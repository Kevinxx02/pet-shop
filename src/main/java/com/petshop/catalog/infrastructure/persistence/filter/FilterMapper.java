package com.petshop.catalog.infrastructure.persistence.filter;

import com.petshop.catalog.domain.filter.Filter;

public class FilterMapper {
    public static FilterJpaEntity toEntity (Filter filter) {
        return new FilterJpaEntity(
                filter.getId(),
                filter.getParentId(),
                filter.getObjectId()
        );
    }

    public static Filter toDomain(FilterJpaEntity entity) {
        return Filter.rehydrate(
                entity.getId(),
                entity.getParentId(),
                entity.getObjectId()
        );
    }
}
