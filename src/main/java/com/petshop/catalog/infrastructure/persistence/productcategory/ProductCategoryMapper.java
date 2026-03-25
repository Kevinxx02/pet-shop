package com.petshop.catalog.infrastructure.persistence.productcategory;

import com.petshop.catalog.domain.productcategory.ProductCategory;
import com.petshop.catalog.infrastructure.persistence.category.CategoryJpaEntity;
import com.petshop.catalog.infrastructure.persistence.product.ProductJpaEntity;
import jakarta.persistence.EntityManager;
import org.springframework.stereotype.Component;

@Component
public class ProductCategoryMapper {
    private final EntityManager entityManager;

    public ProductCategoryMapper(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public ProductCategory toDomain(ProductCategoryJpaEntity productCategory) {
        return ProductCategory.rehydrate(
                productCategory.getId(),
                productCategory.getProductId(),
                productCategory.getCategoryId()
        );
    }

    public ProductCategoryJpaEntity toEntity(ProductCategory productCategory) {
        final CategoryJpaEntity categoryJpaEntity = this.entityManager.getReference(
                CategoryJpaEntity.class,
                productCategory.getCategoryId());
        final ProductJpaEntity productJpaEntity = this.entityManager.getReference(
                ProductJpaEntity.class,
                productCategory.getProductId());

        return new ProductCategoryJpaEntity(
                productCategory.getId(),
                productJpaEntity,
                categoryJpaEntity
        );
    }
}
