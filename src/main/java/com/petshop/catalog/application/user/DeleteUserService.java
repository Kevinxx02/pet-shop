package com.petshop.catalog.application.user;

import com.petshop.catalog.domain.user.UserRepository;
import com.petshop.catalog.infrastructure.persistence.user.UserJpaEntity;
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
        System.out.println(id);
        UserJpaEntity entity = this.userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
        entity.setDeleted(true);
        return true;
    }
}
