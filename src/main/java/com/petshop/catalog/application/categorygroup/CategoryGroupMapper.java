package com.petshop.catalog.application.categorygroup;

import com.petshop.catalog.domain.categorygroup.CategoryGroup;

public class CategoryGroupMapper {
    public static CategoryGroupView toView(CategoryGroup categoryGroup) {
        return new CategoryGroupView(
                categoryGroup.getId(),
                categoryGroup.getGroupId(),
                categoryGroup.getCategoryId()
        );
    }
}
