package io.github.thatyane.sagachoreography.commons.application.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
public class PaymentRequest {

    private Long orderId;
    private Integer userId;
    private BigDecimal amount;

}
