package com.petshop.catalog.domain.productcategory;

import com.petshop.catalog.application.product.ProductView;

import java.util.List;
import java.util.UUID;

public interface ProductCategoryReadRepository {
    List<ProductView> listProductsFromCategory(UUID categoryId);
}