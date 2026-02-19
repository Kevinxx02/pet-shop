package com.petshop.catalog.application.category;


import com.petshop.catalog.domain.category.Category;
import com.petshop.catalog.domain.category.CategoryRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.UUID;

@Service
@Transactional
public class CreateCategoryService {
    private final CategoryRepository categoryRepository;

    public CreateCategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Transactional
    public UUID createCategory(String name, String imageName) throws IOException {
        Category category = new Category(UUID.randomUUID(), name, imageName);

        categoryRepository.save(category);

        return category.id();
    }
}
