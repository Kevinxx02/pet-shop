package com.petshop.catalog.web;

import com.petshop.catalog.application.user.CreateUserService;
import com.petshop.catalog.application.user.DeleteUserService;
import com.petshop.catalog.application.user.GetUserService;
import com.petshop.catalog.application.user.ValidateUserService;
import com.petshop.catalog.application.user.UserView;
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

        final UserView user = createUserService.createUser(
                email,
                password
        );

        final String message = "Se creo el usuario: " + user.email();
        return ResponseEntity.status(200).body(new BaseResponse<>(message, user));
    }

    @GetMapping
    public ResponseEntity<?> listUser() {
        List<UserView> aUser = getUserService.listUsers();
        return ResponseEntity.status(200).body(new BaseResponse<>("Listando usuarios", aUser));
    }

    @PostMapping("/validate")
    public ResponseEntity<?> validateUser(@RequestParam String email,
                                       @RequestParam String password) {
        UserView user = this.validateUserService.validate(
                email,
                password
        );

        final String message = "Bienvenido " + user.email();
        return ResponseEntity.status(200).body(new BaseResponse<>(message, user));
    }

    @DeleteMapping
    public ResponseEntity<?> deleteUser(@RequestParam UUID id) {
        this.deleteUserService.deleteUser(id);

        final String message = "Usuario eliminado";
        return ResponseEntity.status(200).body(new BaseResponse<>(message, new Object()));
    }
}
