package com.petshop.catalog.application.category;


import com.petshop.catalog.domain.category.Category;
import com.petshop.catalog.domain.category.CategoryRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.UUID;

@Service
@Transactional
public class CreateCategoryService {
    private final CategoryRepository categoryRepository;

    @PersistenceContext
    private final EntityManager entityManager;
    public CreateCategoryService(CategoryRepository categoryRepository, EntityManager entityManager) {
        this.categoryRepository = categoryRepository;
        this.entityManager = entityManager;
    }

    @Transactional
    public UUID createCategory(String name, UUID parentId, Boolean isVisible) throws IOException {
        Category category = Category.create(name, parentId, isVisible);
        categoryRepository.save(category);

        return category.getId();
    }
}
