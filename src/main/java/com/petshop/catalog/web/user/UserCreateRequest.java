package com.petshop.catalog.web.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public record UserCreateRequest(
        @NotNull @Email String email,
        @Min(6) String password
) {}