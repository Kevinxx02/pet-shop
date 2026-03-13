package com.petshop.catalog.infrastructure.persistence.blog;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface SpringDataBlogRepository extends JpaRepository<BlogJpaEntity, UUID> {}
