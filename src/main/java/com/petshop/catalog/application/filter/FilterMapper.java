package com.petshop.catalog.application.filter;

import com.petshop.catalog.domain.filter.Filter;

public class FilterMapper {
    public static FilterView toView(Filter filter) {
        return new FilterView(
                filter.getId(),
                filter.getParentId(),
                filter.getObjectId()
        );
    }
}
