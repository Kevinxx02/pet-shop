package com.petshop.catalog.infrastructure.persistence.group;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface SpringDataGroupRepository extends JpaRepository<GroupJpaEntity, UUID> {}
