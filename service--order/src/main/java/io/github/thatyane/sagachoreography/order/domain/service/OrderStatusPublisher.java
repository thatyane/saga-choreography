package io.github.thatyane.sagachoreography.order.domain.service;

import io.github.thatyane.sagachoreography.commons.application.dto.OrderRequest;
import io.github.thatyane.sagachoreography.commons.application.dto.enums.OrderStatus;
import io.github.thatyane.sagachoreography.commons.application.event.OrderEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Sinks;

@Service
@RequiredArgsConstructor
public class OrderStatusPublisher {

    private final Sinks.Many<OrderEvent> orderSinks;

    public void publisher(OrderRequest orderRequest, OrderStatus orderStatus) {
        OrderEvent orderEvent = new OrderEvent(orderRequest, orderStatus);
        orderSinks.tryEmitNext(orderEvent);
    }
}
