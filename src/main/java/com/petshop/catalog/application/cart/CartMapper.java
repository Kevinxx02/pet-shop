package com.petshop.catalog.application.cart;

import com.petshop.catalog.domain.cart.Cart;

public class CartMapper {
    public static CartView toView(Cart cart) {
        return new CartView(
                cart.getId(),
                cart.getStatusId()
        );
    }
}
