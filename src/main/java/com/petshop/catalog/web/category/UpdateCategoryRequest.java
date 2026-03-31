package com.petshop.catalog.web.category;

import java.util.UUID;

public record UpdateCategoryRequest(UUID id, String name, UUID parentId, boolean isVisible) {}