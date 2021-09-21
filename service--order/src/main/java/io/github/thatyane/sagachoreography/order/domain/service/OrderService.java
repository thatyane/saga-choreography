package io.github.thatyane.sagachoreography.order.domain.service;

import io.github.thatyane.sagachoreography.commons.application.dto.OrderRequest;
import io.github.thatyane.sagachoreography.order.domain.entity.PurchaseOrder;
import io.github.thatyane.sagachoreography.order.infrastructure.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static io.github.thatyane.sagachoreography.commons.application.dto.enums.OrderStatus.ORDER_CREATED;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final OrderStatusPublisher orderStatusPublisher;

    @Transactional
    public PurchaseOrder create(OrderRequest orderRequest) {
        PurchaseOrder purchaseOrder = orderRepository.save(PurchaseOrder.builder()
                .productId(orderRequest.getProductId())
                .userId(orderRequest.getUserId())
                .orderStatus(ORDER_CREATED)
                .amount(orderRequest.getAmount())
                .build());

        orderRequest.setOrderId(purchaseOrder.getId());
        orderStatusPublisher.publish(orderRequest, ORDER_CREATED);

        return purchaseOrder;
    }

    public List<PurchaseOrder> findAll() {
        return orderRepository.findAll();
    }
}
