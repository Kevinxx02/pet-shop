package com.petshop.catalog.domain.user;

import java.util.UUID;

public record UserResponse(
        UUID id,
        String email
) {}