package com.petshop.catalog.domain.status;

import java.util.Optional;
import java.util.UUID;

public interface StatusRepository {
    void save(Status status);

    void deleteById(UUID id);

    boolean existsById(UUID id);

    Optional<Status> findByName(String name);
}