package com.petshop.catalog.domain.cartitem;

import java.math.BigDecimal;
import java.util.UUID;

public class CartItem {

    private final UUID id;
    private final UUID productId;
    private int quantity;
    private final BigDecimal unitPrice;

    private CartItem(UUID id, UUID productId, int quantity, BigDecimal unitPrice) {
        validateProduct(productId);
        validateQuantity(quantity);
        validatePrice(unitPrice);

        this.id = id;
        this.productId = productId;
        this.quantity = quantity;
        this.unitPrice = unitPrice;
    }

    // -------------------------
    // FACTORIES
    // -------------------------

    public static CartItem create(UUID productId, int quantity, BigDecimal unitPrice) {
        return new CartItem(
                UUID.randomUUID(),
                productId,
                quantity,
                unitPrice
        );
    }

    public static CartItem rehydrate(UUID id, UUID productId, int quantity, BigDecimal unitPrice) {
        return new CartItem(id, productId, quantity, unitPrice);
    }

    // -------------------------
    // GETTERS
    // -------------------------

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

    // -------------------------
    // BEHAVIOR
    // -------------------------

    public void increaseQuantity(int amount) {
        validateQuantity(amount);
        this.quantity += amount;
    }

    public void decreaseQuantity(int amount) {
        validateQuantity(amount);

        if (this.quantity - amount <= 0) {
            throw new IllegalArgumentException("Quantity cannot be zero or negative");
        }

        this.quantity -= amount;
    }

    public void updateQuantity(int newQuantity) {
        validateQuantity(newQuantity);
        this.quantity = newQuantity;
    }

    public BigDecimal getTotalPrice() {
        return unitPrice.multiply(BigDecimal.valueOf(quantity));
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