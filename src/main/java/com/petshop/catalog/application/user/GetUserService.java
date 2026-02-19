package com.petshop.catalog.application.user;

import com.petshop.catalog.application.product.ProductView;
import com.petshop.catalog.domain.user.User;
import com.petshop.catalog.domain.user.UserRepository;
import com.petshop.catalog.infrastructure.persistence.product.ProductReadRepository;
import com.petshop.catalog.infrastructure.persistence.user.UserJpaEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;

@Service
@Transactional
public class GetUserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public GetUserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public Optional<UserJpaEntity> validateUser(String name, String rawPassword) {
        return userRepository.findByName(name)
                .filter(user ->
                        passwordEncoder.matches(rawPassword, user.getPassword())
                );
    }
}
