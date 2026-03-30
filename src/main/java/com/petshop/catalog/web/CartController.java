package com.petshop.catalog.web;

import com.petshop.catalog.application.cart.CartService;
import com.petshop.catalog.application.cart.CartView;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<BaseResponse<List<CartView>>> list() {
        final List<CartView> carts = this.cartService.findAll();

        final String message = "Lista de carritos";
        return ResponseEntity.status(200).body(new BaseResponse<>(message, carts));

    }

    @GetMapping("/check-availability/{id}")
    public boolean checkAvailability(@PathVariable UUID id) {
        return this.cartService.checkAvailability(id);
    }

    @PostMapping
    public ResponseEntity<BaseResponse<CartView>> create() {
        final CartView cart = this.cartService.create();

        final String message = "Carrito creado";
        return ResponseEntity.status(201).body(new BaseResponse<>(message, cart));
    }
    @PutMapping
    public ResponseEntity<BaseResponse<CartView>> updateStatus(
            UUID id,
            UUID statusId
    ) {
        final CartView cart = this.cartService.updateStatus(id, statusId);

        final String message = "Carrito actualizado";
        return ResponseEntity.status(200).body(new BaseResponse<>(message, cart));
    }
}