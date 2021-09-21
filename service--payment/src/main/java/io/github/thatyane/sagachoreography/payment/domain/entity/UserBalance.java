package io.github.thatyane.sagachoreography.payment.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserBalance {

    @Id
    private Long userId;
    private Integer amount;
}