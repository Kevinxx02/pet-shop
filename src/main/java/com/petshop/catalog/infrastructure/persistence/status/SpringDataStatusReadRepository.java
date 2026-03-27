package com.petshop.catalog.infrastructure.persistence.status;

import com.petshop.catalog.application.status.StatusView;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.UUID;

public interface SpringDataStatusReadRepository extends JpaRepository<StatusJpaEntity, UUID> {

    @Query("""
        SELECT new com.petshop.catalog.application.status.StatusView(
            id,
            name
        )
        FROM StatusJpaEntity
    """)
    List<StatusView> viewAll();
}