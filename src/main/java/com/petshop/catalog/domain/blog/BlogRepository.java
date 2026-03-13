package com.petshop.catalog.domain.blog;

import com.petshop.catalog.infrastructure.persistence.blog.BlogJpaEntity;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface BlogRepository {
    void save(BlogJpaEntity faq);

    BlogJpaEntity toEntity(Blog faq);
    Blog toDomain(BlogJpaEntity faq);
    List<Blog> findAll();
    void deleteById(UUID id);

    Optional<BlogJpaEntity> findById(UUID id);
}
