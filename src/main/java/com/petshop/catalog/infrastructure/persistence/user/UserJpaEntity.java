package com.petshop.catalog.infrastructure.persistence.user;

import jakarta.persistence.*;

import java.util.UUID;
@Entity
@Table(name = "users")
public class UserJpaEntity {

    @Id
    private UUID id;

    private String email;
    private String password;
    private boolean isVisible;


    protected UserJpaEntity() {}

    UserJpaEntity(UUID id, String email, String password, boolean isVisible) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.isVisible = isVisible;
    }

    public UUID getId() {
        return this.id;
    }

    public String getEmail() {
        return this.email;
    }

    public String getPassword() {
        return this.password;
    }

    public boolean getVisible() {
        return this.isVisible;
    }
}