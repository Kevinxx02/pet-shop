package com.petshop.catalog.infrastructure.persistence.productcategory;

import com.petshop.catalog.domain.productcategory.ProductCategoryId;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

import java.util.UUID;

@Entity
@Table(name = "product_category")
public class ProductCategoryJpaEntity {

    @EmbeddedId
    private ProductCategoryId id;

    protected ProductCategoryJpaEntity() {}

    public ProductCategoryJpaEntity(UUID productId, UUID categoryId) {
        this.id = new ProductCategoryId(productId, categoryId);
    }

    public UUID getProductId() { return id.getProductId(); }
    public UUID getCategoryId() { return id.getCategoryId(); }
}

