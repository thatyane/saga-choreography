package io.github.thatyane.sagachoreography.payment.infrastructure.repository;

import io.github.thatyane.sagachoreography.payment.domain.entity.UserBalance;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserBalanceRepository extends JpaRepository<UserBalance, Long> {
}