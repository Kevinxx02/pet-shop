package com.petshop.catalog.domain.user;


import com.petshop.catalog.application.user.UserView;
import com.petshop.catalog.domain.shared.Email;

import java.util.List;
import java.util.Optional;

public interface UserReadRepository {
    List<UserView> findActive();

    Optional<User> findByEmail(Email email);
}