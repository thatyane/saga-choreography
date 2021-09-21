package io.github.thatyane.sagachoreography.commons.application.dto;

import io.github.thatyane.sagachoreography.commons.application.dto.enums.OrderStatus;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class OrderResponse {

    private Long userId;
    private Long productId;
    private Integer amount;
    private Long orderId;
    private OrderStatus orderStatus;
}
