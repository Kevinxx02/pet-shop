package com.petshop.catalog.infrastructure.persistence.cartitem;

import com.petshop.catalog.application.cartItem.CartItemView;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface SpringDataCartItemReadRepository extends JpaRepository<CartItemJpaEntity, UUID> {
    List<CartItemView> findByCartId(UUID cartId);
}
