package com.petshop.catalog.domain.blog;

import java.util.Optional;
import java.util.UUID;

public interface BlogRepository {
    void save(Blog blog);

    Optional<Blog> findById(UUID id);

    boolean existsById(UUID id);
}
