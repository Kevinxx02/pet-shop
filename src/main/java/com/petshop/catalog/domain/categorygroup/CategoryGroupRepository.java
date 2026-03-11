package com.petshop.catalog.domain.categorygroup;

import com.petshop.catalog.infrastructure.persistence.categorygroup.CategoryGroupJpaEntity;

import java.util.List;
import java.util.UUID;

public interface CategoryGroupRepository {
    CategoryGroupJpaEntity toEntity(CategoryGroup multimedia);

    CategoryGroup toDomain(CategoryGroupJpaEntity entity);

    void save(CategoryGroupJpaEntity multimedia);

    List<CategoryGroup> findAll();

    void deleteById(UUID id);
}
