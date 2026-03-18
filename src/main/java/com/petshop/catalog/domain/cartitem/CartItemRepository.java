package com.petshop.catalog.domain.cartitem;

import com.petshop.catalog.domain.cart.Cart;
import com.petshop.catalog.infrastructure.persistence.cartitem.CartItemJpaEntity;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface CartItemRepository {
    void save(CartItemJpaEntity faq);

    List<CartItem> findAll();
    void deleteById(UUID id);

    Optional<CartItemJpaEntity> findById(UUID id);
}
