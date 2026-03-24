package com.petshop.catalog.infrastructure.persistence.user;

import com.petshop.catalog.domain.user.User;

public class UserMapper {
    public static User toDomain(UserJpaEntity entity) {
        return User.rehydrate(
                entity.getId(),
                entity.getEmail(),
                entity.getPassword(),
                entity.getIsActive()
        );
    }

    public static UserJpaEntity toEntity(User domain) {
        return new UserJpaEntity(
                domain.getId().value(),
                domain.getEmail().value(),
                domain.getPassword().value(),
                domain.getIsActive()
        );
    }
}