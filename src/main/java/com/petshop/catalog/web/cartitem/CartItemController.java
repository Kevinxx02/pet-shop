package com.petshop.catalog.web.cartitem;

import com.petshop.catalog.application.cartItem.CartItemService;
import com.petshop.catalog.application.cartItem.CartItemView;
import com.petshop.catalog.web.BaseResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cartItem")
public class CartItemController {
    final CartItemService cartItemService;

    CartItemController(CartItemService cartItemService) {
        this.cartItemService = cartItemService;
    }
    @GetMapping
    public ResponseEntity<BaseResponse<List<CartItemView>>> getItems(
            @RequestBody GetCartItemsRequest request
    ) {
        final List<CartItemView> items = this.cartItemService.getItems(request.cartId());

        final String message = "Lista de items";
        return ResponseEntity.status(200).body(new BaseResponse<>(message, items));
    }

    @PostMapping
    public ResponseEntity<BaseResponse<CartItemView>> createItem(
            @RequestBody CreateCartItemRequest request
    ) {
        final CartItemView cartItem = this.cartItemService.addItem(
                request.cartId(),
                request.productId(),
                request.quantity()
        );


        final String message = "Producto agregado";
        return ResponseEntity.status(201).body(new BaseResponse<>(message, cartItem));
    }

    @PutMapping
    public ResponseEntity<BaseResponse<CartItemView>> updateItem(
            @RequestBody UpdateCartItemRequest request
    ) {
        final CartItemView cartItem = this.cartItemService.updateItem(
                request.id(),
                request.quantity()
        );


        final String message = "Producto modificado";
        return ResponseEntity.status(200).body(new BaseResponse<>(message, cartItem));
    }

    @DeleteMapping
    public void deleteItem(@RequestBody DeleteCartItemRequest request) {
        this.cartItemService.deleteItem(request.id());
    }
}