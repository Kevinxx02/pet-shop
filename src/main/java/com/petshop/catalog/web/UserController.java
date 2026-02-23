package com.petshop.catalog.web;

import com.petshop.catalog.application.user.CreateUserService;
import com.petshop.catalog.application.user.DeleteUserService;
import com.petshop.catalog.application.user.GetUserService;
import com.petshop.catalog.domain.user.User;
import com.petshop.catalog.infrastructure.persistence.user.UserJpaEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/user")
public class UserController {
    private final CreateUserService createUserService;
    private final GetUserService getUserService;
    private final DeleteUserService deleteUserService;

    UserController(CreateUserService createUserService, GetUserService getUserService, DeleteUserService deleteUserService) {
        this.createUserService = createUserService;
        this.getUserService = getUserService;
        this.deleteUserService = deleteUserService;
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
    public List<User> listUser() {
        return getUserService.listUsers();
    }

    @GetMapping("/validate")
    public ResponseEntity<?> validateUser(@RequestParam(required = false) String name,
                                          @RequestParam(required = false) String password) {
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
    @DeleteMapping
    public ResponseEntity<?> deleteUser(@RequestParam UUID id) {
        final Boolean response = deleteUserService.deleteUser(id);
        if (response) {
            return ResponseEntity.ok(new Object() {
                public final String message = "Usuario eliminado";
            });
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
