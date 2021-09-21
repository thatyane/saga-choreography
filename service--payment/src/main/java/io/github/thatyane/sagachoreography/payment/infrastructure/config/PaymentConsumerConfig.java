package io.github.thatyane.sagachoreography.payment.infrastructure.config;

import io.github.thatyane.sagachoreography.commons.application.dto.enums.OrderStatus;
import io.github.thatyane.sagachoreography.commons.application.event.OrderEvent;
import io.github.thatyane.sagachoreography.commons.application.event.PaymentEvent;
import io.github.thatyane.sagachoreography.payment.domain.service.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.function.Function;

@Configuration
@RequiredArgsConstructor
public class PaymentConsumerConfig {

    private final PaymentService paymentService;

    @Bean
    public Function<Flux<OrderEvent>, Flux<PaymentEvent>> paymentProcessor() {
        return orderEventFlux -> orderEventFlux.flatMap(this::processPayment);
    }

    private Mono<PaymentEvent> processPayment(OrderEvent orderEvent) {
        if (OrderStatus.ORDER_CREATED.equals(orderEvent.getOrderStatus())) {
            return Mono.fromSupplier(() -> this.paymentService.newOrderEvent(orderEvent));
        } else {
            return Mono.fromRunnable(() -> this.paymentService.cancelOrderEvent(orderEvent));
        }
    }
}
