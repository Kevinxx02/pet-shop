package com.petshop.catalog.application.user;

import com.petshop.catalog.domain.user.User;
import com.petshop.catalog.domain.user.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;

@Service
@Transactional
public class CreateUserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public CreateUserService(UserRepository userRepository,
                             PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional
    public UserView createUser(String email, String rawPassword) throws IOException {
        String password = passwordEncoder.encode(rawPassword);
        User user = User.create(email, password);

        userRepository.save(user);

        return new UserView(user.getId().value(), user.getEmail().value());
    }
}