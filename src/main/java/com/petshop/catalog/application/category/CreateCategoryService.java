package com.petshop.catalog.application.category;


import com.petshop.catalog.domain.category.Category;
import com.petshop.catalog.domain.category.CategoryRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@Transactional
public class CreateCategoryService {
    private final CategoryRepository categoryRepository;

    public CreateCategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Transactional
    public CategoryView createCategory(String name, UUID parentId, Boolean isVisible) {
        Category category = Category.create(name, parentId, isVisible);
        categoryRepository.save(category);

        return new CategoryView(category.getId(), category.getName(), category.getParentId());
    }
}
