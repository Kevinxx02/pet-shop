package com.petshop.catalog.application.user;

import com.petshop.catalog.domain.shared.Email;
import com.petshop.catalog.domain.user.User;
import com.petshop.catalog.domain.user.UserReadRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

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

    public Optional<User> findByEmail(Email email) {
        return this.userRepository.findByEmail(email);
    }
}
