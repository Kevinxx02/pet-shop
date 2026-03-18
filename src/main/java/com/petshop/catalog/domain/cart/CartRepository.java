package com.petshop.catalog.domain.cart;

import com.petshop.catalog.infrastructure.persistence.cart.CartJpaEntity;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface CartRepository {
    void save(CartJpaEntity faq);

    CartJpaEntity toEntity(Cart cart, CartJpaEntity entity);
    Cart toDomain(CartJpaEntity cart);
    List<Cart> findAll();
    void deleteById(UUID id);

    Optional<CartJpaEntity> findById(UUID id);

    CartJpaEntity findByIdAndStatus(UUID id, String status);
}
