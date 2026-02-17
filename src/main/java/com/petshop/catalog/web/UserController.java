package com.petshop.catalog.web;

import com.petshop.catalog.application.product.CreateProductService;
import com.petshop.catalog.application.user.CreateUserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.UUID;

@RestController
@RequestMapping("/user")
public class UserController {
    private final CreateUserService createUserService;

    UserController(CreateUserService createUserService) {
        this.createUserService = createUserService;
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
}
