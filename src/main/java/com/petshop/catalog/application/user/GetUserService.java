package com.petshop.catalog.application.user;

import com.petshop.catalog.domain.user.UserReadRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class GetUserService {

    private final UserReadRepository userRepository;

    public GetUserService(UserReadRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<UserView> listUsers () {
            return this.userRepository.findActive();
    }
}
