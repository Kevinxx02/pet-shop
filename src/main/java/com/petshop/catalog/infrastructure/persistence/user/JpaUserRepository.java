package com.petshop.catalog.infrastructure.persistence.user;

import com.petshop.catalog.domain.user.User;
import com.petshop.catalog.domain.user.UserRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public class JpaUserRepository implements UserRepository {
    private final SpringDataUsertRepository jpaRepository;

    public JpaUserRepository(SpringDataUsertRepository jpaRepository) {
        this.jpaRepository = jpaRepository;
    }
    public Optional<UserJpaEntity> findById(UUID id) {
        return jpaRepository.findById(id);
    }

    public void save(User user) {
        jpaRepository.save(toEntity(user));
    }
    public void save(UserJpaEntity product) {
        jpaRepository.save(product);
    }

    // Convertir de dominio a JPA
    private UserJpaEntity toEntity(User user) {
        return new UserJpaEntity(
                user.getId(),
                user.getName(),
                user.getPassword()
        );
    }
}
