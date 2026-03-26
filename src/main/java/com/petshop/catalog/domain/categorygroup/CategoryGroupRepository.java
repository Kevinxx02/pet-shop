package com.petshop.catalog.domain.categorygroup;

import java.util.Optional;
import java.util.UUID;

public interface CategoryGroupRepository {
    void save(CategoryGroup categoryGroup);

    Optional<CategoryGroup> findById(UUID id);

    boolean existsByGroupIdAndCategoryIdAndIdNot(UUID groupId, UUID categoryId, UUID id);

    boolean existsByGroupIdAndCategoryId(UUID groupId, UUID categoryId);
}
