package com.petshop.catalog.infrastructure.persistence.productcategory;

import com.petshop.catalog.application.productcategory.ProductCategoryView;
import com.petshop.catalog.domain.productcategory.ProductCategoryReadRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class JpaProductCategoryReadRepository implements ProductCategoryReadRepository {
    final SpringDataProductCategoryReadRepository jpaRepository;
    final ProductCategoryMapper productCategoryMapper;

    JpaProductCategoryReadRepository(
            SpringDataProductCategoryReadRepository jpaRepository,
            ProductCategoryMapper productCategoryMapper
    ) {
        this.jpaRepository = jpaRepository;
        this.productCategoryMapper = productCategoryMapper;
    }

    public List<ProductCategoryView> findAll() {
        return jpaRepository.findAllView();
    }
}
