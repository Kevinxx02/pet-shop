package com.petshop.catalog.application.status;

import com.petshop.catalog.domain.status.Status;

public class StatusMapper {
    public static StatusView toView(Status status) {
        return new StatusView(
                status.getId(),
                status.getName()
        );
    }
}
