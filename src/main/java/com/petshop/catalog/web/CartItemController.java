package com.petshop.catalog.web;

import com.petshop.catalog.application.cartItem.CartItemService;
import com.petshop.catalog.application.cartItem.CartItemView;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/cartItem")
public class CartItemController {
    final CartItemService cartItemService;

    CartItemController(CartItemService cartItemService) {
        this.cartItemService = cartItemService;
    }
    @GetMapping
    public ResponseEntity<BaseResponse<List<CartItemView>>> getItems(
            @RequestParam UUID cartId
    ) {
        final List<CartItemView> items = this.cartItemService.getItems(cartId);

        final String message = "Lista de items";
        return ResponseEntity.status(200).body(new BaseResponse<>(message, items));
    }

    @PostMapping
    public ResponseEntity<BaseResponse<CartItemView>> addItem(
            @RequestParam UUID cartId,
            @RequestParam UUID productId,
            @RequestParam int quantity
    ) {
        final CartItemView cartItem = this.cartItemService.addItem(cartId, productId, quantity);


        final String message = "Producto agregado";
        return ResponseEntity.status(201).body(new BaseResponse<>(message, cartItem));
    }

    @PutMapping
    public ResponseEntity<BaseResponse<CartItemView>> updateItem(
            @RequestParam UUID id,
            int quantity
    ) {
        final CartItemView cartItem = this.cartItemService.updateItem(id, quantity);


        final String message = "Producto modificado";
        return ResponseEntity.status(200).body(new BaseResponse<>(message, cartItem));
    }

    @DeleteMapping
    public void deleteItem(@RequestParam UUID id) {
        this.cartItemService.deleteItem(id);
    }
}