package com.petshop.catalog.domain.status;

import java.util.Objects;
import java.util.UUID;

public class Status {
    private final UUID id;
    private String name;

    private Status(UUID id, String name) {
        this.id = id;
        this.setName(name);
    }

    public static Status create(String name) {
        final UUID id = UUID.randomUUID();
        return new Status(id, name);
    }

    private void setName(String name) {
        if (Objects.equals(this.name, name)) return;

        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Name cannot be empty");

        }

        this.name = name;
    }

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
