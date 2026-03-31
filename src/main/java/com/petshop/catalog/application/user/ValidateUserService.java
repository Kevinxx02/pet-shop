package com.petshop.catalog.application.user;

import com.petshop.catalog.domain.shared.Email;
import com.petshop.catalog.domain.user.User;
import com.petshop.catalog.domain.user.UserReadRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ValidateUserService {

    private final UserReadRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public ValidateUserService(UserReadRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public UserView validate(String email, String rawPassword) {
        final User user = this.userRepository.findByEmail(new Email(email))
                .orElseThrow(() -> new IllegalArgumentException("Invalid credentials"));

        if (!user.getVisible()) {
            throw new IllegalArgumentException("Invalid credentials");
        }

        if (!passwordEncoder.matches(rawPassword, user.getPassword().value())) {
            throw new IllegalArgumentException("Invalid credentials");
        }

        return UserMapper.toView(user);
    }
}
