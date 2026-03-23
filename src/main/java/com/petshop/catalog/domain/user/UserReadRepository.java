package com.petshop.catalog.domain.user;


import com.petshop.catalog.domain.shared.Email;

import java.util.List;
import java.util.Optional;

public interface UserReadRepository {
    Optional<User> findByEmail(Email email);

    List<UserResponse> findActive();
}