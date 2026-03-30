package com.petshop.catalog.infrastructure.persistence.cartitem;

import com.petshop.catalog.domain.cartitem.CartItem;

public class CartItemMapper {
    public static CartItemJpaEntity toEntity(CartItem cartItem) {
        return new CartItemJpaEntity(
                cartItem.getId(),
                cartItem.getCartId(),
                cartItem.getProductId(),
                cartItem.getQuantity(),
                cartItem.getUnitPrice()
        );
    }

    public static CartItem toDomain(CartItemJpaEntity entity) {
        return CartItem.rehydrate(
                entity.getId(),
                entity.getCartId(),
                entity.getProductId(),
                entity.getQuantity(),
                entity.getUnitPrice()
        );
    }
}
