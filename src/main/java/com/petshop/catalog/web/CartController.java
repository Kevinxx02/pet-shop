package com.petshop.catalog.web;

import com.petshop.catalog.application.cart.CartService;
import com.petshop.catalog.application.cart.CartView;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/cart")
public class CartController {
    final CartService cartService;

    CartController(CartService cartService) {
        this.cartService = cartService;
    }

    @GetMapping
    public List<CartView> list() {
        return this.cartService.findAll();
    }

    @GetMapping("/check-availability/{id}")
    public boolean checkAvailability(@PathVariable UUID id) {
        return this.cartService.checkAvailability(id);
    }
    @PostMapping
    public CartView create() {
        return this.cartService.create();
    }
    @PutMapping
    public CartView updateStatus(UUID id, UUID statusId) {
        return this.cartService.updateStatus(id, statusId);
    }
}