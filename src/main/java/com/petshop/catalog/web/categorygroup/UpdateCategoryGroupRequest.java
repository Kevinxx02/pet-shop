package com.petshop.catalog.web.categorygroup;

import java.util.UUID;

public record UpdateCategoryGroupRequest(UUID id, UUID groupId, UUID categoryId, boolean isVisible) {
}