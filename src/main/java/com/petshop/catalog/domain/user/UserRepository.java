package com.petshop.catalog.domain.user;

import com.petshop.catalog.infrastructure.persistence.user.UserJpaEntity;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.UUID;

@Component
public interface UserRepository {
    Optional<UserJpaEntity> findById(UUID id);
    /* Method Overload para que funcione correctamente sin importar el tipo de parametro que se le envie */
    void save(User user);
    void save(UserJpaEntity user);
}
