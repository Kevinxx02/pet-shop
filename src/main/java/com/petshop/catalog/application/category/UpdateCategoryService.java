package com.petshop.catalog.application.category;

import com.petshop.catalog.domain.category.Category;
import com.petshop.catalog.domain.category.CategoryRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@Transactional
public class UpdateCategoryService {
    private final CategoryRepository categoryRepository;

    public UpdateCategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Transactional
    public CategoryView updateCategory(UUID id, String name, UUID parentId, Boolean isVisible) {
        Category category = this.categoryRepository.findById(id);
        if (parentId != null && this.categoryRepository.existsById(parentId)) {
            throw new IllegalArgumentException("El padre no existe");
        }

        category.updateCategory(name, parentId, isVisible);

        this.categoryRepository.save(category);

        return new CategoryView(id, name, parentId);
    }
}
