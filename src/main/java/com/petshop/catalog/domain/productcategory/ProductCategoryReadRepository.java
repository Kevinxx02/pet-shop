package com.petshop.catalog.domain.productcategory;

import com.petshop.catalog.application.productcategory.ProductCategoryView;

import java.util.List;

public interface ProductCategoryReadRepository {
    List<ProductCategoryView> findAll();
}