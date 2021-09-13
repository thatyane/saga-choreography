package io.github.thatyane.sagachoreography.commons.application.event;

import io.github.thatyane.sagachoreography.commons.application.dto.OrderRequest;
import io.github.thatyane.sagachoreography.commons.application.dto.enums.OrderStatus;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@NoArgsConstructor
public class OrderEvent implements Event {

    private UUID eventId = UUID.randomUUID();
    private LocalDateTime eventDate = LocalDateTime.now();
    private OrderRequest orderRequest;
    private OrderStatus orderStatus;

    public OrderEvent(OrderRequest orderRequest, OrderStatus orderStatus) {
        this.orderRequest = orderRequest;
        this.orderStatus = orderStatus;
    }

    @Override
    public UUID getEventId() {
        return this.eventId;
    }

    @Override
    public LocalDateTime getEventDate() {
        return this.eventDate;
    }

}
