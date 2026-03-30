package com.petshop.catalog.infrastructure.persistence.cartitem;

import com.petshop.catalog.domain.cartitem.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface SpringDataCartItemRepository extends JpaRepository<CartItemJpaEntity, UUID> {
    Optional<CartItem> findByCartIdAndProductId(UUID cartId, UUID productId);

    boolean existsByCartIdAndProductId(UUID cartId, UUID productId);
}
