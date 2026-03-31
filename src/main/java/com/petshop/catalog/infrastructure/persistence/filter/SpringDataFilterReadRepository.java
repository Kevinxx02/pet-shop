package com.petshop.catalog.infrastructure.persistence.filter;

import com.petshop.catalog.application.filter.FilterView;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.UUID;

public interface SpringDataFilterReadRepository extends JpaRepository<FilterJpaEntity, UUID> {
    @Query("""
        SELECT new com.petshop.catalog.application.filter.FilterView(
            id,
            parentId,
            objectId
        )
        FROM FilterJpaEntity
""")
    List<FilterView> viewAll();
}
