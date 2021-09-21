package io.github.thatyane.sagachoreography.payment.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class UserTransaction {

    @Id
    private Long orderId;
    private Long userId;
    private Integer amount;
}