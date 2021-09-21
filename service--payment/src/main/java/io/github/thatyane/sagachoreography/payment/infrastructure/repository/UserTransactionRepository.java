package io.github.thatyane.sagachoreography.payment.infrastructure.repository;

import io.github.thatyane.sagachoreography.payment.domain.entity.UserTransaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserTransactionRepository extends JpaRepository<UserTransaction, Long> {
}