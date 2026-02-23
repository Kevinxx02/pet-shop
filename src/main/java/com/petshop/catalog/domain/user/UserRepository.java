package com.petshop.catalog.domain.user;

import com.petshop.catalog.infrastructure.persistence.user.UserJpaEntity;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Component
public interface UserRepository {
    /* Method Overload para que funcione correctamente sin importar el tipo de parametro que se le envie */
    void save(User user);

    Optional<UserJpaEntity> findByName(String name);

    Optional<UserJpaEntity> findById(UUID id);

    List<User> findVisible();
}
