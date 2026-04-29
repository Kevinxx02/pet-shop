package com.petshop.catalog.infrastructure.persistence.user;

import com.petshop.catalog.domain.user.User;

public class UserMapper {
    public static UserJpaEntity toEntity(User user) {
        return new UserJpaEntity(
                user.getId().value(),
                user.getEmail().value(),
                user.getPassword(),
                user.getVisible(),
                user.getRole()
        );
    }

    public static User toDomain(UserJpaEntity entity) {
        return User.rehydrate(
                entity.getId(),
                entity.getEmail(),
                entity.getPassword(),
                entity.getVisible(),
                entity.getRole()
        );
    }
}