package com.petshop.catalog.infrastructure.persistence.productcategory;


import com.petshop.catalog.domain.productcategory.ProductCategory;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public class JpaProductCategoryRepository implements ProductCategoryRepository {
    private final SpringDataProductCategoryRepository jpaRepository;

    public JpaProductCategoryRepository(SpringDataProductCategoryRepository jpaRepository) {
        this.jpaRepository = jpaRepository;
    }

    public List<ProductCategory> findAll() {
        return jpaRepository.findAll().stream().map(this::toDomain).toList();
    }

    @Override
    public List<ProductCategoryJpaEntity> findByCategoryId(UUID categoryId) {
        return jpaRepository.findByIdCategoryId(categoryId);
    }


    public void save(ProductCategory productCategory) {
        jpaRepository.save(toEntity(productCategory));
    }

    // Convertir de dominio a JPA
    private ProductCategoryJpaEntity toEntity(ProductCategory productCategory) {
        return new ProductCategoryJpaEntity(
                productCategory.getProductId(),
                productCategory.getCategoryId()
        );
    }

    /* Convertir de jpa a dominio */
    private ProductCategory toDomain(ProductCategoryJpaEntity productCategory) {
        return ProductCategory.create(productCategory.getProductId(), productCategory.getCategoryId());
    }
}

