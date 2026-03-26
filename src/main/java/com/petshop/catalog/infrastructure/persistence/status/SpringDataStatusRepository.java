package com.petshop.catalog.infrastructure.persistence.status;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface SpringDataStatusRepository extends JpaRepository<StatusJpaEntity, UUID> {}
