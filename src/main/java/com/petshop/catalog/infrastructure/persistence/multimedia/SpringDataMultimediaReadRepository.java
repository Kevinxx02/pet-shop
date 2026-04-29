package com.petshop.catalog.infrastructure.persistence.multimedia;

import com.petshop.catalog.application.multimedia.MultimediaView;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.UUID;

public interface SpringDataMultimediaReadRepository extends JpaRepository<MultimediaJpaEntity, UUID> {
    @Query("""
    SELECT
        new com.petshop.catalog.application.multimedia.MultimediaView(id, fileName, isPrimary, ownerId)
    FROM MultimediaJpaEntity c
""")
    List<MultimediaView> viewAll();
}