package com.petshop.catalog.infrastructure.persistence.blog;

import com.petshop.catalog.application.blog.BlogView;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.UUID;

public interface SpringDataBlogReadRepository extends JpaRepository<BlogJpaEntity, UUID> {
    @Query("""
        SELECT new com.petshop.catalog.application.blog.BlogView(
            id,
            title,
            date,
            url
        )
        FROM BlogJpaEntity
        WHERE isVisible = true
""")
    List<BlogView> viewAll();
}
