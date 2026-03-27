package com.petshop.catalog.infrastructure.persistence.cart;

import com.petshop.catalog.domain.cart.Cart;

public class CartMapper {
    public static CartJpaEntity toEntity(Cart cart) {
        return new CartJpaEntity(
                cart.getId(),
                cart.getStatusId(),
                cart.getCreatedAt()
        );
    }

    public static Cart toDomain(CartJpaEntity entity) {
        return Cart.rehydrate(
                entity.getId(),
                entity.getStatusId(),
                entity.getCreatedAt()
        );
    }
}
