package com.petshop.catalog.web.productcategory;

import java.util.UUID;

public record CreateProductCategoryRequest(UUID productId, UUID categoryId) {}