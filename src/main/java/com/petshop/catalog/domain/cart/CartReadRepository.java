package com.petshop.catalog.domain.cart;

import com.petshop.catalog.application.cart.CartView;

import java.util.List;
public interface CartReadRepository {
    List<CartView> viewAll();
}
