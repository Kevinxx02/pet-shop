package com.petshop.catalog.application.cart;

import com.petshop.catalog.domain.cart.Cart;
import com.petshop.catalog.domain.cart.CartRepository;
import com.petshop.catalog.domain.product.ProductRepository;
import com.petshop.catalog.infrastructure.persistence.cart.CartJpaEntity;
import com.petshop.catalog.infrastructure.persistence.product.ProductJpaEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@Transactional
public class CartService {

    private final CartRepository cartRepository;
    private final ProductRepository productRepository;

    public CartService(CartRepository cartRepository,
                       ProductRepository productRepository) {
        this.cartRepository = cartRepository;
        this.productRepository = productRepository;
    }

    // -------------------------
    // FIND
    // -------------------------

    public List<Cart> findAll() {
        return cartRepository.findAll();
    }

    public Cart findByIdAndStatus(UUID id) {
        CartJpaEntity entity = cartRepository.findByIdAndStatus(id, "Pending");
        return cartRepository.toDomain(entity);
    }

    // -------------------------
    // CREATE
    // -------------------------

    public Cart create() {

        Cart cart = Cart.create();

        CartJpaEntity entity = cartRepository.toEntity(cart, new CartJpaEntity());

        cartRepository.save(entity);

        return cart;
    }

    // -------------------------
    // ADD PRODUCT
    // -------------------------

    public Cart addProduct(UUID cartId, UUID productId, Integer quantity) {

        CartJpaEntity entity = cartRepository.findById(cartId)
                .orElseThrow(() -> new RuntimeException("Cart not found"));

        ProductJpaEntity product = productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found"));

        Cart cart = cartRepository.toDomain(entity);

        // 🔴 AHORA delegas al dominio
        cart.addProduct(productId, quantity, product.getPrice());

        CartJpaEntity updated = cartRepository.toEntity(cart, entity);

        cartRepository.save(updated);

        return cart;
    }

    // -------------------------
    // REMOVE ITEM
    // -------------------------

    public Cart removeItem(UUID cartId, UUID itemId) {

        CartJpaEntity entity = cartRepository.findById(cartId)
                .orElseThrow(() -> new RuntimeException("Cart not found"));

        Cart cart = cartRepository.toDomain(entity);

        cart.removeItem(itemId);

        CartJpaEntity updated = cartRepository.toEntity(cart, entity);

        cartRepository.save(updated);

        return cart;
    }

    // -------------------------
    // UPDATE STATUS
    // -------------------------

    public UUID updateStatus(UUID id, String status) {

        CartJpaEntity entity = cartRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cart not found"));

        entity.setStatus(status);

        return id;
    }

    public Cart updateItemQuantity(UUID cartId, UUID itemId, Integer quantity) {
        CartJpaEntity entity = cartRepository.findById(cartId)
                .orElseThrow(() -> new RuntimeException("Cart not found"));

        Cart cart = cartRepository.toDomain(entity);
        cart.updateItemQuantity(itemId, quantity);

        CartJpaEntity updated = cartRepository.toEntity(cart, entity);

        cartRepository.save(updated);

        return cart;
    }
}