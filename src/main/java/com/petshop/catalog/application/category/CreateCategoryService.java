package com.petshop.catalog.application.category;


import com.petshop.catalog.domain.category.Category;
import com.petshop.catalog.domain.category.CategoryRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class CreateCategoryService {
    private final CategoryRepository categoryRepository;

    public CreateCategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Transactional
    public CategoryView createCategory(String name) {
        Category category = Category.create(name);
        categoryRepository.save(category);

        return CategoryMapper.toView(category);
    }
}
