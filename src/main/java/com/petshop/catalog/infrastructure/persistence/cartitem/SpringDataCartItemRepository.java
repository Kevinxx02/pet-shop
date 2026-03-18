package com.petshop.catalog.infrastructure.persistence.cartitem;

import com.petshop.catalog.infrastructure.persistence.cart.CartJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface SpringDataCartItemRepository extends JpaRepository<CartItemJpaEntity, UUID> {}
