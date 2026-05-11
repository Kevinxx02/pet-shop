package com.petshop.catalog.infrastructure.persistence.user;

import com.petshop.catalog.application.user.CreateUserService;
import com.petshop.catalog.application.user.GetUserService;
import com.petshop.catalog.domain.shared.Email;
import com.petshop.catalog.domain.user.User;
import com.petshop.catalog.infrastructure.persistence.AbstractIntegrationTest;
import com.petshop.catalog.web.exception.UserAlreadyExistsException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class UserRepositoryIntegrationTest
        extends AbstractIntegrationTest {
    @Autowired
    private CreateUserService createUserService;

    @Autowired
    private GetUserService getUserService;

    @Autowired
    SpringDataUserRepository springDataUserRepository;

    @BeforeEach
    void cleanDatabase() {
        springDataUserRepository.deleteAll();
    }

    @Test
    void shouldPersistUser() {
        final String email = "test@test.com";
        final String rawPassword = "password";

        createUserService.createUser(email, rawPassword);

        Optional<User> user = getUserService.findByEmail(new Email(email));
        assertTrue(user.isPresent());
    }

    @Test
    void shouldNotAllowDuplicateEmail() {
        createUserService.createUser("test@test.com", "password");

        assertThrows(UserAlreadyExistsException.class, () ->
                createUserService.createUser("test@test.com", "pass2")
        );
    }
}