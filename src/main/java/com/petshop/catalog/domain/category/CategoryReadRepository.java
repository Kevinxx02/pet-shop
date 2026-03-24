package com.petshop.catalog.domain.category;

import com.petshop.catalog.application.category.CategoryView;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface CategoryReadRepository {
    List<CategoryView> findVisible();
}
