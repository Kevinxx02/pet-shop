package com.petshop.catalog.domain.cartitem;

import java.math.BigDecimal;
import java.util.UUID;

public class CartItem {
    private final UUID id;
    private final UUID productId;
    private int quantity;
    private final BigDecimal unitPrice;
    private final UUID cartId;

    private CartItem(UUID id, UUID cartId, UUID productId, int quantity, BigDecimal unitPrice) {
        validateProduct(productId);
        validateQuantity(quantity);
        validatePrice(unitPrice);

        this.id = id;
        this.productId = productId;
        this.quantity = quantity;
        this.unitPrice = unitPrice;
        this.cartId = cartId;
    }

    public static CartItem create(UUID cartId, UUID productId, int quantity, BigDecimal unitPrice) {
        return new CartItem(
                UUID.randomUUID(),
                cartId,
                productId,
                quantity,
                unitPrice
        );
    }

    public static CartItem rehydrate(UUID id, UUID cartId, UUID productId, int quantity, BigDecimal unitPrice) {
        return new CartItem(id, cartId, productId, quantity, unitPrice);
    }

    public UUID getId() {
        return id;
    }

    public UUID getProductId() {
        return productId;
    }

    public int getQuantity() {
        return quantity;
    }

    public BigDecimal getUnitPrice() {
        return unitPrice;
    }

    public UUID getCartId() {
        return cartId;
    }
// -------------------------
    // BEHAVIOR
    // -------------------------

    public void updateQuantity(int newQuantity) {
        validateQuantity(newQuantity);
        this.quantity = newQuantity;
    }

    // -------------------------
    // VALIDATIONS
    // -------------------------

    private void validateProduct(UUID productId) {
        if (productId == null) {
            throw new IllegalArgumentException("ProductId cannot be null");
        }
    }

    private void validateQuantity(int quantity) {
        if (quantity <= 0) {
            throw new IllegalArgumentException("Quantity must be greater than zero");
        }
    }

    private void validatePrice(BigDecimal price) {
        if (price == null || price.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("Price must be >= 0");
        }
    }
}