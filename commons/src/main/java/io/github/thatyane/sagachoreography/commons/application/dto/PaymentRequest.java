package io.github.thatyane.sagachoreography.commons.application.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class PaymentRequest {

    private Long orderId;
    private Long userId;
    private Integer amount;

}
