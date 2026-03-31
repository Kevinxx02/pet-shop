package com.petshop.catalog.domain.filter;

import java.util.Optional;
import java.util.UUID;

public interface FilterRepository {
    void save(Filter filter);

    Optional<Filter> findById(UUID id);

    boolean existsById(UUID id);

    void deleteById(UUID id);
}
