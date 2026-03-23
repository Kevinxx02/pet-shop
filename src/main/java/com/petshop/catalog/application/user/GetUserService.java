package com.petshop.catalog.application.user;

import com.petshop.catalog.domain.user.UserReadRepository;
import com.petshop.catalog.domain.user.UserResponse;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class GetUserService {

    private final UserReadRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public GetUserService(UserReadRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public List<UserResponse> listUsers () {
            return this.userRepository.findActive();
    }
}
