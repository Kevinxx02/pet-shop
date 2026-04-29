package com.petshop.catalog.infrastructure.persistence.user;

import com.petshop.catalog.domain.user.Role;
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

    @Enumerated(EnumType.STRING)
    private Role role;

    protected UserJpaEntity() {}

    UserJpaEntity(
            UUID id,
            String email,
            String password,
            boolean isVisible,
            Role role
    ) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.isVisible = isVisible;
        this.role = role;
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
    public Role getRole() {
        return this.role;
    }
}