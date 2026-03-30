package com.petshop.catalog.application.cartItem;


import com.petshop.catalog.domain.cartitem.CartItem;

public class CartItemMapper {
    public static CartItemView toView(CartItem cartItem) {
        return new CartItemView(
                cartItem.getId(),
                cartItem.getProductId(),
                cartItem.getQuantity(),
                cartItem.getUnitPrice()
        );
    }
}
