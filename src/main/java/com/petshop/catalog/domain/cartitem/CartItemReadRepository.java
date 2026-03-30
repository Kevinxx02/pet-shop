package com.petshop.catalog.domain.cartitem;

import com.petshop.catalog.application.cartItem.CartItemView;

import java.util.List;
import java.util.UUID;

public interface CartItemReadRepository {
    List<CartItemView> findByCartId(UUID cartId);
}
