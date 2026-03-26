package com.petshop.catalog.domain.status;

import com.petshop.catalog.application.status.StatusView;

import java.util.List;

public interface StatusReadRepository {
    List<StatusView> viewAll();
}
