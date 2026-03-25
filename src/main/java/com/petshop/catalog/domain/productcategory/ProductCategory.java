package com.petshop.catalog.domain.productcategory;

import java.util.UUID;

public class ProductCategory {

    private final UUID id;
    private final UUID productId;
    private final UUID categoryId;

    private ProductCategory(UUID id, UUID productId, UUID categoryId) {
        if (productId == null) {
            throw new IllegalArgumentException("productId cannot be null");
        }

        if (categoryId == null) {
            throw new IllegalArgumentException("categoryId cannot be null");
        }

        this.id = id;
        this.productId = productId;
        this.categoryId = categoryId;
    }

    public static ProductCategory create(UUID productId, UUID categoryId) {
        final UUID newId = UUID.randomUUID();

        return new ProductCategory(newId, productId, categoryId);
    }

    public static ProductCategory rehydrate(UUID id, UUID productId, UUID categoryId) {
        return new ProductCategory(id, productId, categoryId);
    }

    public UUID getProductId() { return productId; }
    public UUID getCategoryId() { return categoryId; }

    public UUID getId() {
        return this.id;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ProductCategory)) return false;
        ProductCategory that = (ProductCategory) o;
        return id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }
}
