package com.petshop.catalog.infrastructure.persistence.productcategory;

import com.petshop.catalog.application.product.ProductView;
import com.petshop.catalog.application.productcategory.ProductCategoryView;
import com.petshop.catalog.domain.productcategory.ProductCategory;
import com.petshop.catalog.domain.productcategory.ProductCategoryReadRepository;
import com.petshop.catalog.domain.user.User;
import com.petshop.catalog.infrastructure.persistence.user.UserJpaEntity;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public class JpaProductCategoryReadRepository implements ProductCategoryReadRepository {
    final SpringDataProductCategoryReadRepository springDataProductCategoryReadRepository;

    JpaProductCategoryReadRepository(SpringDataProductCategoryReadRepository springDataProductCategoryReadRepository) {
        this.springDataProductCategoryReadRepository = springDataProductCategoryReadRepository;
    }

    public List<ProductCategory> findAll() {
        return springDataProductCategoryReadRepository.findAll().stream().map(this::toDomain).toList();
    }

    @Override
    public List<ProductCategoryView> findWithCategoryName() {
        return springDataProductCategoryReadRepository.findWithCategoryName();
    }

    private ProductCategory toDomain(ProductCategoryJpaEntity productCategory) {
        return ProductCategory.rehydrate(productCategory.getId(), productCategory.getProduct().getId(), productCategory.getCategory().getId());
    }
}
