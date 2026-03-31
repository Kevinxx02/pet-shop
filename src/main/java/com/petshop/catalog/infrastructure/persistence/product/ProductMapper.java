package com.petshop.catalog.infrastructure.persistence.product;

import com.petshop.catalog.domain.product.Product;

public class ProductMapper {
    public static ProductJpaEntity toEntity(Product product) {
        return new ProductJpaEntity(
                product.getId(),
                product.getName(),
                product.getDescription(),
                product.getPrice().value(),
                product.getVisible()
        );
    }

    public static Product toDomain(ProductJpaEntity entity) {
        return Product.rehydrate(
                entity.getId(),
                entity.getName(),
                entity.getDescription(),
                entity.getPrice(),
                entity.getIsVisible());
    }
}
