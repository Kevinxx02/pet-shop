package com.petshop.catalog.infrastructure.persistence.status;

import com.petshop.catalog.domain.status.Status;

public class StatusMapper {
    public static StatusJpaEntity toEntity(Status status) {
        return new StatusJpaEntity(
                status.getId(),
                status.getName()
        );
    }
}
