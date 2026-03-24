package com.petshop.catalog.infrastructure.persistence.category;

import com.petshop.catalog.domain.category.Category;
import jakarta.persistence.EntityManager;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class CategoryMapper {

    private final EntityManager entityManager;

    public CategoryMapper(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public Category toDomain(CategoryJpaEntity category) {
        final CategoryJpaEntity parent = category.getParent();
        final UUID parentId = (parent != null) ? parent.getId() : null;

        return Category.rehydrate(
                category.getId(),
                category.getName(),
                parentId,
                category.getIsVisible()
        );
    }

    public CategoryJpaEntity toEntity(Category category) {
        CategoryJpaEntity parent = null;

        if (category.getParentId() != null) {
            parent = entityManager.getReference(
                    CategoryJpaEntity.class,
                    category.getParentId()
            );
        }

        return new CategoryJpaEntity(
                category.getId(),
                category.getName(),
                parent,
                category.getIsVisible()
        );
    }
}