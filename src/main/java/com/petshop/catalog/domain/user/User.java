package com.petshop.catalog.domain.user;

import com.petshop.catalog.domain.shared.Email;

import java.util.UUID;

public class User{

    private UserId id;
    private Email email;
    private HashedPassword password;
    private boolean isActive;

    private User(UUID id, String email, String password, boolean active) {
        this.id = new UserId(id);
        this.email = new Email(email);
        this.password = new HashedPassword(password);
        this.isActive = active;
    }

    public static User create(String email, String password) {
        final boolean isActive = true;
        final UUID id = UUID.randomUUID();

        return new User(id, email, password, isActive);
    }
    public static User rehydrate(UUID id, String email, String password, boolean isActive) {
        return new User(id, email, password, isActive);
    }

    public void changePassword(HashedPassword newPassword) {
        this.password = newPassword;
    }

    public UserId getId() {
        return id;
    }

    public boolean getIsActive() {
        return isActive;
    }

    public HashedPassword getPassword() {
        return password;
    }

    public Email getEmail() {
        return email;
    }

    public void deActivateUser() {
        this.isActive = false;
    }
}