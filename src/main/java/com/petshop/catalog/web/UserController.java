package com.petshop.catalog.web;

import com.petshop.catalog.application.user.CreateUserService;
import com.petshop.catalog.application.user.GetUserService;
import com.petshop.catalog.infrastructure.persistence.user.UserJpaEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/user")
public class UserController {
    private final CreateUserService createUserService;
    private final GetUserService getUserService;

    UserController(CreateUserService createUserService, GetUserService getUserService) {
        this.createUserService = createUserService;
        this.getUserService = getUserService;
    }
    @PostMapping
    public ResponseEntity<?> createUser(
            @RequestParam String name,
            @RequestParam String password) throws IOException {

        final UUID id = createUserService.createUser(
                name,
                password
        );

        return ResponseEntity.ok(new Object() {
            public final String message = "Usuario creado correctamente";
            public final UUID productId = id;
        });
    }
    @GetMapping
    public ResponseEntity<?> validateUser(@RequestParam String name,
                                          @RequestParam String password) {
        Optional<UserJpaEntity> user = getUserService.validateUser(
                name,
                password
        );

        if (user.isPresent()) {
            return ResponseEntity.ok(new Object() {
                public final String message = "Usuario validado";
                public final Optional<UserJpaEntity> id = user;
            });
        }

        return ResponseEntity.noContent().build();
    }
}
