package com.petshop.catalog.application.user;

import com.petshop.catalog.domain.user.User;
import com.petshop.catalog.domain.user.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@Transactional
public class DeleteUserService {
    private final UserRepository userRepository;
    public DeleteUserService(UserRepository userRepository) {

        this.userRepository = userRepository;
    }

    @Transactional
    public Boolean deleteUser(UUID id) {
        User user = this.userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
        user.deActivateUser();

        this.userRepository.save(user);
        return true;
    }
}
