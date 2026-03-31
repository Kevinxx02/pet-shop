package com.petshop.catalog.web.user;

import com.petshop.catalog.application.user.*;
import com.petshop.catalog.web.BaseResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
    private final CreateUserService createUserService;
    private final GetUserService getUserService;
    private final ValidateUserService validateUserService;
    private final UpdateUserService updateUserService;

    UserController(CreateUserService createUserService,
                   GetUserService getUserService,
                   ValidateUserService validateUserService,
                   UpdateUserService updateUserService
    ) {
        this.createUserService = createUserService;
        this.getUserService = getUserService;
        this.updateUserService = updateUserService;
        this.validateUserService = validateUserService;
    }

    @GetMapping
    public ResponseEntity<BaseResponse<List<UserView>>> listUser() {
        List<UserView> aUser = getUserService.listUsers();
        return ResponseEntity.status(200).body(new BaseResponse<>("Listando usuarios", aUser));
    }

    @PostMapping
    public ResponseEntity<BaseResponse<UserView>> createUser(@RequestBody UserCreateRequest request) {

        final UserView user = createUserService.createUser(
                request.email(),
                request.password()
        );

        final String message = "Se creo el usuario: " + user.email();
        return ResponseEntity.status(200).body(new BaseResponse<>(message, user));
    }

    @PostMapping("/validate")
    public ResponseEntity<BaseResponse<UserView>> validateUser(@RequestBody UserValidateRequest request) {
        final UserView user = this.validateUserService.validate(
                request.email(),
                request.password()
        );

        final String message = "Bienvenido " + user.email();
        return ResponseEntity.status(200).body(new BaseResponse<>(message, user));
    }

    @PutMapping
    public ResponseEntity<BaseResponse<UserView>> updateUser(@RequestBody UserUpdateRequest request) {
        final UserView user = this.updateUserService.updateUser(
                request.id(),
                request.email(),
                request.password(),
                request.isVisible()
        );

        final String message = "Usuario actualizado";
        return ResponseEntity.status(200).body(new BaseResponse<>(message, user));
    }
}
