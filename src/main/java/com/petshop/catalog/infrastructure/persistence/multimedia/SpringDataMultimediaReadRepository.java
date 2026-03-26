package com.petshop.catalog.infrastructure.persistence.multimedia;

import com.petshop.catalog.application.multimedia.MultimediaView;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.UUID;

public interface SpringDataMultimediaReadRepository extends JpaRepository<MultimediaJpaEntity, UUID> {
    @Query("""
    SELECT new com.petshop.catalog.application.multimedia.MultimediaView(
        c.id, c.name, c.parent.id
    )
    FROM CategoryJpaEntity c
    WHERE c.isVisible = true
""")
    List<MultimediaView> findAllView();
}
