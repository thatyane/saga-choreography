package io.github.thatyane.sagachoreography.order.domain.entity;

import io.github.thatyane.sagachoreography.commons.application.dto.enums.OrderStatus;
import io.github.thatyane.sagachoreography.commons.application.dto.enums.PaymentStatus;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "PURCHASE_ORDER")
@Data
@NoArgsConstructor
@Builder
public class PurchaseOrder {

    @Id
    @GeneratedValue
    private Long id;

    private Integer userId;
    private Integer productId;
    private BigDecimal price;

    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;

    @Enumerated(EnumType.STRING)
    private PaymentStatus paymentStatus;
}