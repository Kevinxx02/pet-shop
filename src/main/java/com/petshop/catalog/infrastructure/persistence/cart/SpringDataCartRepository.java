package com.petshop.catalog.infrastructure.persistence.cart;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface SpringDataCartRepository extends JpaRepository<CartJpaEntity, UUID> {
    boolean existsByIdAndStatusId(UUID id, UUID statusId);
}
