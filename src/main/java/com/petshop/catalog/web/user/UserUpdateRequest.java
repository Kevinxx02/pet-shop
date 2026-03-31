package com.petshop.catalog.web.user;

import java.util.UUID;

public record UserUpdateRequest(UUID id, String email, String password, boolean isVisible) {}
