package com.petshop.catalog.infrastructure.persistence.user;

import com.petshop.catalog.domain.shared.Email;
import com.petshop.catalog.domain.user.User;
import com.petshop.catalog.domain.user.UserReadRepository;
import com.petshop.catalog.application.user.UserView;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class JpaUserReadRepository implements UserReadRepository {
    private final SpringDataUserReadRepository jpaRepository;

    public JpaUserReadRepository(SpringDataUserReadRepository jpaRepository) {
        this.jpaRepository = jpaRepository;
    }

    @Override
    public Optional<User> findByEmail(Email email) {
        return jpaRepository.findByEmail(email.value())
                .map(UserMapper::toDomain);
    }

    @Override
    public List<UserView> findActive() {
        return jpaRepository.findActive()
                .stream()
                .toList();
    }
}
