package com.petshop.catalog.domain.categorygroup;

import com.petshop.catalog.application.categorygroup.CategoryGroupView;

import java.util.List;

public interface CategoryGroupReadRepository {
    List<CategoryGroupView> findAll();
}
