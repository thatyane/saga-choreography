package io.github.thatyane.sagachoreography.order.infrastructure.config;

import io.github.thatyane.sagachoreography.commons.application.event.PaymentEvent;
import io.github.thatyane.sagachoreography.order.domain.handler.OrderStatusUpdateHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.function.Consumer;

@Configuration
@RequiredArgsConstructor
public class EventConsumerConfig {

    private final OrderStatusUpdateHandler handler;

    @Bean
    public Consumer<PaymentEvent> paymentEventConsumer() {
        return (payment) -> handler.updateOrder(payment.getPaymentRequestOrderId(), po ->
                po.setPaymentStatus(payment.getPaymentStatus()));
    }
}