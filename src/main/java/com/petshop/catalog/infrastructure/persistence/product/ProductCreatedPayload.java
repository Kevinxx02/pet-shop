package com.petshop.catalog.infrastructure.persistence.product;

import java.util.UUID;

public class ProductCreatedPayload {
    private final UUID productId;

    private ProductCreatedPayload(UUID productId) {
        this.productId = productId;
    }

    public static ProductCreatedPayload create(UUID productId) {
        return new ProductCreatedPayload(productId);
    }
    public UUID getProductId() {
        return this.productId;
    }
}
