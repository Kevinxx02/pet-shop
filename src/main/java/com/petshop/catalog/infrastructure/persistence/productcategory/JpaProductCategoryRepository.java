package com.petshop.catalog.infrastructure.persistence.productcategory;


import com.petshop.catalog.domain.productcategory.ProductCategory;
import com.petshop.catalog.domain.productcategory.ProductCategoryRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public class JpaProductCategoryRepository implements ProductCategoryRepository {
    private final SpringDataProductCategoryRepository jpaRepository;
    private final ProductCategoryMapper productCategoryMapper;

    public JpaProductCategoryRepository(SpringDataProductCategoryRepository jpaRepository, ProductCategoryMapper productCategoryMapper) {
        this.jpaRepository = jpaRepository;
        this.productCategoryMapper = productCategoryMapper;
    }

    public void save(ProductCategory productCategory) {
        jpaRepository.save(this.productCategoryMapper.toEntity(productCategory));
    }

    @Override
    public void deleteById(UUID id) {
        jpaRepository.deleteById(id);
    }

    @Override
    public boolean existsById(UUID id) {
        return jpaRepository.existsById(id);
    }

    @Override
    public boolean existsByProductIdAndCategoryId(UUID productId, UUID categoryId) {
        return jpaRepository.existsByProduct_IdAndCategory_Id(productId, categoryId);
    }
}