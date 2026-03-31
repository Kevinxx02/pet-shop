package com.petshop.catalog.infrastructure.persistence.productcategory;

import com.petshop.catalog.domain.productcategory.ProductCategory;
import org.springframework.stereotype.Component;

@Component
public class ProductCategoryMapper {
    public ProductCategoryMapper() {}

    public static ProductCategory toDomain(ProductCategoryJpaEntity productCategory) {
        return ProductCategory.rehydrate(
                productCategory.getId(),
                productCategory.getProductId(),
                productCategory.getCategoryId()
        );
    }

    public static ProductCategoryJpaEntity toEntity(ProductCategory productCategory) {

        return new ProductCategoryJpaEntity(
                productCategory.getId(),
                productCategory.getProductId(),
                productCategory.getCategoryId()
        );
    }
}
