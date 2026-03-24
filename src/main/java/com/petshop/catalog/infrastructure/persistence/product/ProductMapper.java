package com.petshop.catalog.infrastructure.persistence.product;

import com.petshop.catalog.domain.product.Product;

public class ProductMapper {
    static ProductJpaEntity toEntity(Product product) {
        ProductJpaEntity entity = new ProductJpaEntity();
        entity.setId(product.getId());
        entity.setName(product.getName());
        entity.setDescription(product.getDescription());
        entity.setPrice(product.getPrice().value());
        entity.setVisibility(product.getVisible());

        return entity;
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
