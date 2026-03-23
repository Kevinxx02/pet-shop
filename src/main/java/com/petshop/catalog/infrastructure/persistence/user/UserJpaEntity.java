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
    private boolean isActive;


    protected UserJpaEntity() {}

    UserJpaEntity(UUID id, String email, String password, boolean isActive) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.isActive = isActive;
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

    public boolean getIsActive() {
        return this.isActive;
    }
}