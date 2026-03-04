package com.petshop.catalog.infrastructure.persistence.productcategory;

import com.petshop.catalog.domain.productcategory.ProductCategoryId;
import com.petshop.catalog.infrastructure.persistence.category.CategoryJpaEntity;
import com.petshop.catalog.infrastructure.persistence.product.ProductJpaEntity;
import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table(name = "product_category")
public class ProductCategoryJpaEntity {

    @EmbeddedId
    private ProductCategoryId id;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("productId")
    @JoinColumn(name = "product_id")
    private ProductJpaEntity product;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("categoryId")
    @JoinColumn(name = "category_id")
    private CategoryJpaEntity category;

    protected ProductCategoryJpaEntity() {}

    public ProductCategoryJpaEntity(ProductJpaEntity product, CategoryJpaEntity category) {
        this.product = product;
        this.category = category;
        this.id = new ProductCategoryId(product.getId(), category.getId());
    }

    public ProductJpaEntity getProduct() { return product; }
    public CategoryJpaEntity getCategory() { return category; }
}