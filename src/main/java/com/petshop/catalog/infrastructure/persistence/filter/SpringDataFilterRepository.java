package com.petshop.catalog.infrastructure.persistence.filter;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface SpringDataFilterRepository extends JpaRepository<FilterJpaEntity, UUID> {}
