package com.petshop.catalog.infrastructure.persistence.status;

import com.petshop.catalog.domain.status.Status;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface SpringDataStatusRepository extends JpaRepository<StatusJpaEntity, UUID> {
    Optional<Status> findByName(String name);
}
