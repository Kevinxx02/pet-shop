package com.petshop.catalog.application.category;

import com.petshop.catalog.domain.category.Category;

public class CategoryMapper {
    public static CategoryView toView(Category category) {
        return new CategoryView(category.getId(), category.getName());
    }
}
