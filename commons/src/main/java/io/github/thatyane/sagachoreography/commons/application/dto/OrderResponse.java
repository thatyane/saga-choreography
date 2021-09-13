package io.github.thatyane.sagachoreography.commons.application.dto;

import io.github.thatyane.sagachoreography.commons.application.dto.enums.OrderStatus;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
public class OrderResponse {

    private Integer userId;
    private Integer productId;
    private BigDecimal amount;
    private Long orderId;
    private OrderStatus orderStatus;
}
