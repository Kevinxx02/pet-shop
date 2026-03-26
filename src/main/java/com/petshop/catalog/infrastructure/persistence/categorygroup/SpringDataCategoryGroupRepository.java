package com.petshop.catalog.infrastructure.persistence.categorygroup;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface SpringDataCategoryGroupRepository extends JpaRepository<CategoryGroupJpaEntity, UUID> {
    boolean existsByGroupIdAndCategoryIdAndIdNot(UUID groupId, UUID categoryId, UUID id);

    boolean existsByGroupIdAndCategoryId(UUID groupId, UUID categoryId);
}
