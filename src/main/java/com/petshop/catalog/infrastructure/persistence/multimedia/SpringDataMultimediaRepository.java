package com.petshop.catalog.infrastructure.persistence.multimedia;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface SpringDataMultimediaRepository extends JpaRepository<MultimediaJpaEntity, UUID> {}
