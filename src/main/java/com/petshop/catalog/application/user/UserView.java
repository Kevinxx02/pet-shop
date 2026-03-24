package com.petshop.catalog.application.user;

import java.util.UUID;

public record UserView(
        UUID id,
        String email
) {}