package com.petshop.catalog.infrastructure.persistence.user;

import com.petshop.catalog.domain.user.User;
import com.petshop.catalog.domain.user.UserRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public class JpaUserRepository implements UserRepository {
    private final SpringDataUserRepository jpaRepository;

    public JpaUserRepository(SpringDataUserRepository jpaRepository) {
        this.jpaRepository = jpaRepository;
    }

    @Override
    public User save(User user) {
        UserJpaEntity entity = Mapper.toEntity(user);
        jpaRepository.save(entity);

        return user;
    }

    @Override
    public Optional<User> findById(UUID id) {
        return jpaRepository.findById(id)
                .map(Mapper::toDomain);
    }
}
