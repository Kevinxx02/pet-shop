package com.petshop.catalog.infrastructure.persistence.user;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface SpringDataUsertRepository extends JpaRepository<UserJpaEntity, UUID> {
    Optional<UserJpaEntity> findByName(String name);
}