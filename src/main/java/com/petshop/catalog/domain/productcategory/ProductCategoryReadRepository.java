package com.petshop.catalog.domain.productcategory;

import com.petshop.catalog.application.product.ProductView;
import com.petshop.catalog.application.productcategory.ProductCategoryView;

import java.util.List;
import java.util.UUID;

public interface ProductCategoryReadRepository {
    List<ProductCategory> findAll();

    List<ProductCategoryView> findWithCategoryName();
}