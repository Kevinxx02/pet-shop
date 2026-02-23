package com.petshop.catalog.domain.productcategory;

import jakarta.persistence.Embeddable;

import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

@Embeddable
public class ProductCategoryId implements Serializable {

    private UUID productId;
    private UUID categoryId;

    protected ProductCategoryId() {}

    public ProductCategoryId(UUID productId, UUID categoryId) {
        this.productId = productId;
        this.categoryId = categoryId;
    }

    public UUID getProductId() {
        return this.productId;
    }

    public UUID getCategoryId() {
        return this.categoryId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ProductCategoryId)) return false;
        ProductCategoryId that = (ProductCategoryId) o;

        return Objects.equals(this.productId, (that.getProductId()))
                && Objects.equals(this.categoryId, (that.getCategoryId()));
    }

    public int hashCode() {
        return Objects.hash(this.productId, this.categoryId);
    }
}
