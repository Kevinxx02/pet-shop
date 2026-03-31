package com.petshop.catalog.application.user;

import com.petshop.catalog.domain.user.User;

public class UserMapper {
    public static UserView toView(User user) {
        return new UserView(user.getId().value(), user.getEmail().value());
    }
}
