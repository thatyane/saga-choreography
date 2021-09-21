package io.github.thatyane.sagachoreography.order.domain.handler;

import io.github.thatyane.sagachoreography.commons.application.dto.OrderRequest;
import io.github.thatyane.sagachoreography.commons.application.dto.enums.OrderStatus;
import io.github.thatyane.sagachoreography.commons.application.dto.enums.PaymentStatus;
import io.github.thatyane.sagachoreography.order.domain.entity.PurchaseOrder;
import io.github.thatyane.sagachoreography.order.domain.service.OrderStatusPublisher;
import io.github.thatyane.sagachoreography.order.infrastructure.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.Transactional;

import java.util.function.Consumer;

@Configuration
@RequiredArgsConstructor
public class OrderStatusUpdateHandler {

    private final OrderRepository orderRepository;
    private final OrderStatusPublisher orderStatusPublisher;

    @Transactional
    public void updateOrder(Long id, Consumer<PurchaseOrder> consumer) {
        orderRepository.findById(id).ifPresent(consumer.andThen(this::updateOrder));
    }

    private void updateOrder(PurchaseOrder purchaseOrder) {
        boolean isPaymentComplete = PaymentStatus.PAYMENT_COMPLETED.equals(purchaseOrder.getPaymentStatus());
        OrderStatus orderStatus = isPaymentComplete ? OrderStatus.ORDER_COMPLETED : OrderStatus.ORDER_CANCELLED;
        purchaseOrder.setOrderStatus(orderStatus);
        if (!isPaymentComplete) {
            orderStatusPublisher.publish(convertEntityToDto(purchaseOrder), orderStatus);
        }
    }

    public OrderRequest convertEntityToDto(PurchaseOrder purchaseOrder) {
        return OrderRequest.builder()
                .orderId(purchaseOrder.getId())
                .userId(purchaseOrder.getUserId())
                .amount(purchaseOrder.getAmount())
                .productId(purchaseOrder.getProductId())
                .build();
    }
}