package com.petshop.catalog.infrastructure.persistence.faq;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface SpringDataFaqRepository extends JpaRepository<FaqJpaEntity, UUID> {}
