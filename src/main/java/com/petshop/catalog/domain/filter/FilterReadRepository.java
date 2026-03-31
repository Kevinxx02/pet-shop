package com.petshop.catalog.domain.filter;

import com.petshop.catalog.application.filter.FilterView;

import java.util.List;

public interface FilterReadRepository {
    List<FilterView> viewAll();
}
