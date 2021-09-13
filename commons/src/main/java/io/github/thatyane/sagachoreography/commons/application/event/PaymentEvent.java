package io.github.thatyane.sagachoreography.commons.application.event;

import io.github.thatyane.sagachoreography.commons.application.dto.PaymentRequest;
import io.github.thatyane.sagachoreography.commons.application.dto.enums.PaymentStatus;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@NoArgsConstructor
public class PaymentEvent implements Event {

    private UUID eventId = UUID.randomUUID();
    private LocalDateTime eventDate = LocalDateTime.now();
    private PaymentRequest paymentRequest;
    private PaymentStatus paymentStatus;

    public PaymentEvent(PaymentRequest paymentRequest, PaymentStatus paymentStatus) {
        this.paymentRequest = paymentRequest;
        this.paymentStatus = paymentStatus;
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
