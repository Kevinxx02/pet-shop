package com.petshop.catalog.infrastructure.persistence.user;


import com.petshop.catalog.application.user.UserView;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface SpringDataUserReadRepository extends JpaRepository<UserJpaEntity, UUID> {
    Optional<UserJpaEntity> findByEmail(String value);
    @Query("""
    SELECT new com.petshop.catalog.application.user.UserView(
        u.id, u.email
    )
    FROM UserJpaEntity u
    WHERE u.isActive = true
""")
    List<UserView> findActive();
}