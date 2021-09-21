package io.github.thatyane.sagachoreography.order.domain.entity;

import io.github.thatyane.sagachoreography.commons.application.dto.enums.OrderStatus;
import io.github.thatyane.sagachoreography.commons.application.dto.enums.PaymentStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "PURCHASE_ORDER")
@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class PurchaseOrder {

    @Id
    @GeneratedValue
    private Long id;

    private Long userId;
    private Long productId;
    private Integer amount;

    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;

    @Enumerated(EnumType.STRING)
    private PaymentStatus paymentStatus;
}