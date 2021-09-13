package io.github.thatyane.sagachoreography.order.infrastructure.repository;

import io.github.thatyane.sagachoreography.order.domain.entity.PurchaseOrder;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<PurchaseOrder, Long> {
}
