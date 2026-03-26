package com.petshop.catalog.domain.status;

import java.util.UUID;

public interface StatusRepository {
    void save(Status status);

    void deleteById(UUID id);

    boolean existsById(UUID id);
}