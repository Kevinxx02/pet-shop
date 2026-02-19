package com.petshop.catalog.domain.user;

import com.petshop.catalog.infrastructure.persistence.user.UserJpaEntity;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public interface UserRepository {
    /* Method Overload para que funcione correctamente sin importar el tipo de parametro que se le envie */
    void save(User user);

    Optional<UserJpaEntity> findByName(String name);
}
