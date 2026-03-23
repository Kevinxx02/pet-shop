package com.petshop.catalog.domain.shared;

import java.util.regex.Pattern;

public record Email(String value) {

    private static final Pattern EMAIL_PATTERN =
            Pattern.compile("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$");

    public Email {
        if (value == null) {
            throw new IllegalArgumentException("Email cannot be null");
        }

        value = value.trim();

        if (value.isEmpty()) {
            throw new IllegalArgumentException("Email cannot be empty");
        }

        if (!EMAIL_PATTERN.matcher(value).matches()) {
            throw new IllegalArgumentException("Invalid email format");
        }

        // Normalización (importante)
        value = value.toLowerCase();
    }

    @Override
    public String toString() {
        return value;
    }
}