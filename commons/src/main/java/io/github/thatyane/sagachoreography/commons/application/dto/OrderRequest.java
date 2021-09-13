package io.github.thatyane.sagachoreography.commons.application.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
public class OrderRequest {

    private Integer userId;
    private Integer productId;
    private BigDecimal amount;
    private Long orderId;
}
