package com.petshop.catalog.infrastructure.persistence.productcategory;

import com.petshop.catalog.infrastructure.persistence.category.CategoryJpaEntity;
import com.petshop.catalog.infrastructure.persistence.product.ProductJpaEntity;
import jakarta.persistence.*;

import java.util.UUID;
@Entity
@Table(name = "product_category")
public class ProductCategoryJpaEntity {
    @Id
    @Column(columnDefinition = "uuid")
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    private ProductJpaEntity product;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    private CategoryJpaEntity category;

    protected ProductCategoryJpaEntity() {}

    public ProductCategoryJpaEntity(UUID id, ProductJpaEntity product, CategoryJpaEntity category) {
        this.id = id;
        this.product = product;
        this.category = category;
    }

    public UUID getId() { return id; }

    public ProductJpaEntity getProduct() { return product; }

    public CategoryJpaEntity getCategory() { return category; }

    public UUID getProductId() { return product.getId(); }

    public UUID getCategoryId() { return category.getId(); }
}