package com.petshop.catalog.infrastructure.persistence.product;

import java.util.UUID;

public class ProductUpdatedPayload {
    private final UUID productId;

    private ProductUpdatedPayload(UUID productId) {
        this.productId = productId;
    }

    public static ProductUpdatedPayload create(UUID productId) {
        return new ProductUpdatedPayload(productId);
    }
}
