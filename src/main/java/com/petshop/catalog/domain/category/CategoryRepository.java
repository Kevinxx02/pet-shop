package com.petshop.catalog.domain.category;

import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public interface CategoryRepository {
    void save(Category user);

    Category findById(UUID Id);

    Boolean existsById(UUID categoryId);
}
