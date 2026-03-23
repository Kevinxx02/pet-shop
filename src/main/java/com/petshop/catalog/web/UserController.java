package com.petshop.catalog.web;

import com.petshop.catalog.application.user.CreateUserService;
import com.petshop.catalog.application.user.DeleteUserService;
import com.petshop.catalog.application.user.GetUserService;
import com.petshop.catalog.application.user.ValidateUserService;
import com.petshop.catalog.domain.user.UserResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/user")
public class UserController {
    private final CreateUserService createUserService;
    private final GetUserService getUserService;
    private final DeleteUserService deleteUserService;
    private final ValidateUserService validateUserService;

    UserController(CreateUserService createUserService, GetUserService getUserService, DeleteUserService deleteUserService, ValidateUserService validateUserService) {
        this.createUserService = createUserService;
        this.getUserService = getUserService;
        this.deleteUserService = deleteUserService;
        this.validateUserService = validateUserService;
    }
    @PostMapping
    public ResponseEntity<?> createUser(
            @RequestParam String email,
            @RequestParam String password) throws IOException {

        final UUID id = createUserService.createUser(
                email,
                password
        );

        return ResponseEntity.ok(new Object() {
            public final String message = "Usuario creado correctamente";
            public final UUID userId = id;
        });
    }

    @GetMapping
    public List<UserResponse> listUser() {
        return getUserService.listUsers();
    }

    @PostMapping("/validate")
    public UserResponse validateUser(@RequestParam String email,
                                       @RequestParam String password) {
        UserResponse user = this.validateUserService.validate(
                email,
                password
        );

        /* Debo crear un DTO o un read Respository */
        return user;
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
