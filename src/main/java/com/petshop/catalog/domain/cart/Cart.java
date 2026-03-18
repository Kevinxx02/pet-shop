package com.petshop.catalog.domain.cart;

import com.petshop.catalog.domain.cartitem.CartItem;

import java.math.BigDecimal;
import java.util.*;

public class Cart {

    private UUID id;
    private String status;
    private List<CartItem> items;
    private Date createdAt;

    private Cart(UUID id, String status, List<CartItem> items, Date createdAt) {
        this.id = id;
        this.status = status;
        this.items = new ArrayList<>(items);
        this.createdAt = createdAt;
    }

    // -------------------------
    // FACTORIES
    // -------------------------

    public static Cart create() {
        return new Cart(
                UUID.randomUUID(),
                "Pending",
                new ArrayList<>(),
                new Date()
        );
    }

    public static Cart rehydrate(UUID id, String status, List<CartItem> items, Date createdAt) {
        return new Cart(id, status, items, createdAt);
    }

    // -------------------------
    // GETTERS
    // -------------------------

    public UUID getId() {
        return id;
    }

    public String getStatus() {
        return status;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public List<CartItem> getItems() {
        return Collections.unmodifiableList(items);
    }

    // -------------------------
    // BEHAVIOR (CORE)
    // -------------------------

    public void addProduct(UUID productId, int quantity, BigDecimal unitPrice) {

        validateQuantity(quantity);

        Optional<CartItem> existing = findItemByProduct(productId);

        if (existing.isPresent()) {
            existing.get().increaseQuantity(quantity);
            return;
        }

        CartItem newItem = CartItem.create(productId, quantity, unitPrice);

        this.items.add(newItem);
    }

    public void removeItem(UUID itemId) {

        boolean removed = this.items.removeIf(i -> i.getId().equals(itemId));

        if (!removed) {
            throw new RuntimeException("Item not found in cart");
        }
    }

    public void updateItemQuantity(UUID itemId, int newQuantity) {

        validateQuantity(newQuantity);

        CartItem item = findItemById(itemId)
                .orElseThrow(() -> new RuntimeException("Item not found"));

        item.updateQuantity(newQuantity);
    }

    public void clear() {
        this.items.clear();
    }

    // -------------------------
    // INTERNAL HELPERS
    // -------------------------

    private Optional<CartItem> findItemByProduct(UUID productId) {
        return this.items.stream()
                .filter(i -> i.getProductId().equals(productId))
                .findFirst();
    }

    private Optional<CartItem> findItemById(UUID itemId) {
        return this.items.stream()
                .filter(i -> i.getId().equals(itemId))
                .findFirst();
    }

    private void validateQuantity(int quantity) {
        if (quantity <= 0) {
            throw new IllegalArgumentException("Quantity must be greater than zero");
        }
    }
}