package com.petshop.catalog.infrastructure.persistence.category;


import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface SpringDataCategoryRepository extends JpaRepository<CategoryJpaEntity, UUID> {
    List<CategoryJpaEntity> findByIsCreator(Boolean isCreator);
}
