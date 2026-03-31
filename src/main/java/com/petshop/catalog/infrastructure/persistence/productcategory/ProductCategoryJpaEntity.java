package com.petshop.catalog.infrastructure.persistence.productcategory;

import jakarta.persistence.*;

import java.util.UUID;
@Entity
@Table(name = "product_category")
public class ProductCategoryJpaEntity {
    @Id
    @Column(columnDefinition = "uuid")
    private UUID id;

    @Column(name = "product_id", nullable = false)
    private UUID productId;

    @Column(name = "category_id", nullable = false)
    private UUID categoryId;

    protected ProductCategoryJpaEntity() {}

    public ProductCategoryJpaEntity(UUID id, UUID productId, UUID categoryId) {
        this.id = id;
        this.setCategoryId(categoryId);
        this.setProductId(productId);
    }

    public UUID getId() { return id; }

    public UUID getProductId() { return this.productId; }

    public UUID getCategoryId() { return this.categoryId; }

    public void setProductId(UUID productId) {
        this.productId = productId;
    }
    public void setCategoryId(UUID categoryId) {
        this.categoryId = categoryId;
    }
}