package com.petshop.catalog.web;

import com.petshop.catalog.application.cart.CartService;
import com.petshop.catalog.domain.cart.Cart;
import com.petshop.catalog.domain.cartitem.CartItem;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/cart")
public class CartController {
    /* El servicio */
    final CartService cartService;

    /* Constructor */
    CartController(CartService cartService) {
        this.cartService = cartService;
    }

    /* Lo que devuelve cuando se usa el metodo get */
    @GetMapping
    public List<Cart> list() {
        return this.cartService.findAll();
    }
    /* Lo que hace cuando recibe el metodo POST */

    @GetMapping("/{id}")
    public Cart find(@PathVariable UUID id) {
        return this.cartService.findByIdAndStatus(id);
    }

    /* Lo que hace cuando recibe el metodo POST */
    @PostMapping
    public Cart create() {
        return this.cartService.create();
    }

    @PostMapping("/addProduct")
    public Cart create(@RequestParam UUID id, @RequestParam UUID productId, @RequestParam Integer quantity) {
        return this.cartService.addProduct(id, productId, quantity);
    }

    @PutMapping
    public UUID update(@RequestParam UUID id,
                       @RequestParam String status,
                       @RequestParam List<CartItem> items) {
        return UUID.randomUUID();
    }


    @PutMapping("/updateQuantity")
    public Cart updateQuantity(@RequestParam UUID cartId,
                       @RequestParam UUID itemId,
                       @RequestParam Integer quantity) {

        return this.cartService.updateItemQuantity(cartId, itemId, quantity);
    }

    /* Lo que hace cuando recibe el metodo delete */
    @DeleteMapping("{cartId}/removeItem/{itemId}")
    public Cart delete(@PathVariable UUID cartId, @PathVariable UUID itemId) {
        return this.cartService.removeItem(cartId, itemId);
    }
}