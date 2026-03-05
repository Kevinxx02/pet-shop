package com.petshop.catalog.application.productcategory;

import java.util.UUID;

public record ProductCategoryView(
        UUID id,
        UUID productId,
        UUID categoryId,
        String categoryName
) {}
