package com.petshop.catalog.domain.user;

import org.springframework.security.crypto.password.PasswordEncoder;

public class HashedPassword {
    private final PasswordEncoder passwordEncoder;

    public HashedPassword(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    static String fromString(String value) {
        if (value == null) {
            throw new IllegalArgumentException("Password hash cannot be null");
        }

        value = value.trim();

        if (value.isEmpty()) {
            throw new IllegalArgumentException("Password hash cannot be empty");
        }

        // Validación básica (ej: BCrypt empieza con $2)
        if (!value.startsWith("$2")) {
            throw new IllegalArgumentException("Invalid password hash format");
        }

        return value;
    }

    @Override
    public String toString() {
        return "[PROTECTED]";
    }
}