package com.petshop.catalog.infrastructure.persistence.categorygroup;

import com.petshop.catalog.domain.categorygroup.CategoryGroup;

public class CategoryGroupMapper {

    public static CategoryGroup toDomain(CategoryGroupJpaEntity entity) {
        return CategoryGroup.rehydrate(
                entity.getId(),
                entity.getGroupId(),
                entity.getCategoryId(),
                entity.getVisible()
        );
    }

    public static CategoryGroupJpaEntity toEntity(CategoryGroup categoryGroup) {
        return new CategoryGroupJpaEntity(
                categoryGroup.getId(),
                categoryGroup.getGroupId(),
                categoryGroup.getCategoryId(),
                categoryGroup.getVisible()
        );
    }
}
