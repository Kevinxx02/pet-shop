package com.petshop.catalog.application.category;

import com.petshop.catalog.domain.category.CategoryReadRepository;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class GetCategoryService {
    private final CategoryReadRepository categoryRepository;

    public GetCategoryService(CategoryReadRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public List<CategoryView> list() {
        return categoryRepository.findVisible();
    }
}
