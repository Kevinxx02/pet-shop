package com.petshop.catalog.web.category;

import java.util.UUID;

public record CreateCategoryRequest(String name, UUID parentId) {}
