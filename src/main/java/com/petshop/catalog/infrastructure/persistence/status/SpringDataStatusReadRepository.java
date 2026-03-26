package com.petshop.catalog.infrastructure.persistence.status;

import com.petshop.catalog.application.status.StatusView;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface SpringDataStatusReadRepository extends JpaRepository<StatusJpaEntity, UUID> {
    List<StatusView> viewAll();
}
