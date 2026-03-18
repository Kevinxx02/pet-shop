package com.petshop.catalog.infrastructure.persistence.cart;

import com.petshop.catalog.domain.cart.Cart;
import com.petshop.catalog.infrastructure.persistence.blog.BlogJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface SpringDataCartRepository extends JpaRepository<CartJpaEntity, UUID> {
    CartJpaEntity findByIdAndStatus(UUID id, String status);
}
