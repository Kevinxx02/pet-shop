package com.petshop.catalog.infrastructure.persistence.user;

import com.petshop.catalog.infrastructure.persistence.product.ProductJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface SpringDataUsertRepository extends JpaRepository<UserJpaEntity, UUID> {}