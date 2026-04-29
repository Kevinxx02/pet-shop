package com.petshop.catalog.infrastructure.persistence.category;

import com.petshop.catalog.domain.category.Category;
import jakarta.persistence.EntityManager;
import org.springframework.stereotype.Component;

@Component
public class CategoryMapper {

    private final EntityManager entityManager;

    public CategoryMapper(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public Category toDomain(CategoryJpaEntity category) {
        return Category.rehydrate(
                category.getId(),
                category.getName(),
                category.getIsVisible()
        );
    }

    public CategoryJpaEntity toEntity(Category category) {
        return new CategoryJpaEntity(
                category.getId(),
                category.getName(),
                category.getIsVisible()
        );
    }
}