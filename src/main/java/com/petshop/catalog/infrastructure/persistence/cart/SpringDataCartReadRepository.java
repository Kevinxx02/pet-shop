package com.petshop.catalog.infrastructure.persistence.cart;

import com.petshop.catalog.application.cart.CartView;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.UUID;

public interface SpringDataCartReadRepository extends JpaRepository<CartJpaEntity, UUID> {
    @Query("""
    SELECT new com.petshop.catalog.application.cart.CartView(
        id,
        statusId
    )
    FROM CartJpaEntity
""")
    List<CartView> viewAll();
}
