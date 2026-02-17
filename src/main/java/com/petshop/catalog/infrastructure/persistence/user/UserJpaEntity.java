package com.petshop.catalog.infrastructure.persistence.user;

import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table(name = "users")
public class UserJpaEntity {

    @Id
    @Column(columnDefinition = "uuid")
    private UUID id;

    private String name;
    private String password;
    @Version
    private Long version;
    public UserJpaEntity() {} // constructor por JPA

    public UserJpaEntity(UUID id, String name, String password) {
        this.id = id;
        this.name = name;
        this.password = password;
    }
    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
