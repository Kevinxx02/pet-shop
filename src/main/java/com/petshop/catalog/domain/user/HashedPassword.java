package com.petshop.catalog.domain.user;

public record HashedPassword(String value) {

    public HashedPassword {
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
    }

    @Override
    public String toString() {
        return "[PROTECTED]";
    }
}