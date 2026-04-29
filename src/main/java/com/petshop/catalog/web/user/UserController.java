package com.petshop.catalog.web.user;

import com.petshop.catalog.application.user.*;
import com.petshop.catalog.domain.shared.Email;
import com.petshop.catalog.domain.user.User;
import com.petshop.catalog.infrastructure.security.JwtService;
import com.petshop.catalog.web.BaseResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
    private final CreateUserService createUserService;
    private final GetUserService getUserService;
    private final AuthUserService authUserService;
    private final UpdateUserService updateUserService;
    private final JwtService jwtService;

    UserController(CreateUserService createUserService,
                   GetUserService getUserService,
                   AuthUserService authUserService,
                   UpdateUserService updateUserService,
                   JwtService jwtService
    ) {
        this.createUserService = createUserService;
        this.getUserService = getUserService;
        this.updateUserService = updateUserService;
        this.authUserService = authUserService;
        this.jwtService = jwtService;
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
    public ResponseEntity<BaseResponse<AuthResponse>> validateUser(
            @RequestBody UserValidateRequest request
    ) {
        final AuthResponse token = this.authUserService.login(
                request.email(),
                request.password()
        );

        return ResponseEntity.status(200).body(new BaseResponse<>("", token));
    }
    @PostMapping("/refresh")
    public String refresh(@RequestBody RefreshRequest request) {
        System.out.println("Entro");
        if (!jwtService.isValid(request.getRefreshToken())) {
            throw new RuntimeException("Invalid refresh token");
        }

        String username = jwtService.extractUsername(request.getRefreshToken());

        // ⚠️ aquí puedes recargar roles desde DB (mejor práctica)

        // ✔ obtener usuario real
        User user = getUserService.findByEmail(new Email(username))
                .orElseThrow(() -> new IllegalArgumentException("Invalid credentials"));

        List<String> roles = List.of("ROLE_" + user.getRole().toString());

        return jwtService.generateToken(username, roles);
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
