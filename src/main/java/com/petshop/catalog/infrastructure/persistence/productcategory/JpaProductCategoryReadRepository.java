package com.petshop.catalog.infrastructure.persistence.productcategory;

import com.petshop.catalog.application.product.ProductView;
import com.petshop.catalog.domain.productcategory.ProductCategoryReadRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public class JpaProductCategoryReadRepository implements ProductCategoryReadRepository {
    final SpringDataProductCategoryReadRepository springDataProductCategoryReadRepository;

    JpaProductCategoryReadRepository(SpringDataProductCategoryReadRepository springDataProductCategoryReadRepository) {
        this.springDataProductCategoryReadRepository = springDataProductCategoryReadRepository;
    }

    @Override
    public List<ProductView> listProductsFromCategory(UUID categoryId) {
        return springDataProductCategoryReadRepository.listProductsFromCategory(categoryId);
    }
}
