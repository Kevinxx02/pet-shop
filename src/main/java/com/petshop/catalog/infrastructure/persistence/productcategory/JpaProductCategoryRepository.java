package com.petshop.catalog.infrastructure.persistence.productcategory;


import com.petshop.catalog.domain.productcategory.ProductCategory;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public class JpaProductCategoryRepository implements ProductCategoryRepository {
    private final SpringDataProductCategoryRepository jpaRepository;

    public JpaProductCategoryRepository(SpringDataProductCategoryRepository jpaRepository) {
        this.jpaRepository = jpaRepository;
    }

    public void save(ProductCategory productCategory) {
        jpaRepository.save(toEntity(productCategory));
    }

    @Override
    public void deleteByIdProductId(UUID productId) {
        jpaRepository.deleteByIdProductId(productId);
    }

    // Convertir de dominio a JPA
    private ProductCategoryJpaEntity toEntity(ProductCategory productCategory) {
        return new ProductCategoryJpaEntity(
                productCategory.getProductId(),
                productCategory.getCategoryId()
        );
    }
}

