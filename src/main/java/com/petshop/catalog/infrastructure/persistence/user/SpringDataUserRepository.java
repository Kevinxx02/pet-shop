package com.petshop.catalog.infrastructure.persistence.user;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface SpringDataUserRepository extends JpaRepository<UserJpaEntity, UUID> {
}