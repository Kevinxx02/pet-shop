package com.petshop.catalog.web.categorygroup;

import java.util.UUID;

public record CreateCategoryGroupRequest(UUID groupId, UUID categoryId) {}