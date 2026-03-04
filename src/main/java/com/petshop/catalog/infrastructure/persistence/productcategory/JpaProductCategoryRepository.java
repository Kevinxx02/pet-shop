package com.petshop.catalog.infrastructure.persistence.productcategory;


import com.petshop.catalog.domain.productcategory.ProductCategory;
import com.petshop.catalog.infrastructure.persistence.category.CategoryJpaEntity;
import com.petshop.catalog.infrastructure.persistence.product.ProductJpaEntity;
import jakarta.persistence.EntityManager;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public class JpaProductCategoryRepository implements ProductCategoryRepository {
    private final SpringDataProductCategoryRepository jpaRepository;
    private final EntityManager entityManager;
    public JpaProductCategoryRepository(SpringDataProductCategoryRepository jpaRepository, EntityManager entityManager) {
        this.jpaRepository = jpaRepository;
        this.entityManager = entityManager;
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
        final CategoryJpaEntity categoryJpaEntity = entityManager.getReference(
                CategoryJpaEntity.class,
                productCategory.getCategoryId());
        final ProductJpaEntity productJpaEntity = entityManager.getReference(
                ProductJpaEntity.class,
                productCategory.getProductId());

        return new ProductCategoryJpaEntity(
                productJpaEntity,
                categoryJpaEntity
        );
    }
}

