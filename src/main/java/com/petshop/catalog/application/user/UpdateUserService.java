package com.petshop.catalog.application.user;

import com.petshop.catalog.domain.user.User;
import com.petshop.catalog.domain.user.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@Transactional
public class UpdateUserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UpdateUserService(UserRepository userRepository,
                             PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional
    public UserView updateUser(UUID id, String email, String rawPassword, boolean isVisible) {
        final User user = this.userRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Usuario no encontrado"));
        String password = passwordEncoder.encode(rawPassword);

        user.update(email, password, isVisible);

        userRepository.save(user);

        return UserMapper.toView(user);
    }
}