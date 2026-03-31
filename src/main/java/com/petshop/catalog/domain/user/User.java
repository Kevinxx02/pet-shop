package com.petshop.catalog.domain.user;

import com.petshop.catalog.domain.shared.Email;

import java.util.UUID;

public class User{

    private UserId id;
    private Email email;
    private HashedPassword password;
    private boolean isVisible;

    private User(UUID id, String email, String password, boolean active) {
        this.id = new UserId(id);
        this.email = new Email(email);
        this.password = new HashedPassword(password);
        this.isVisible = active;
    }

    public static User create(String email, String password) {
        final boolean isVisible = true;
        final UUID id = UUID.randomUUID();

        return new User(id, email, password, isVisible);
    }
    public static User rehydrate(UUID id, String email, String password, boolean isVisible) {
        return new User(id, email, password, isVisible);
    }

    public void changePassword(HashedPassword newPassword) {
        this.password = newPassword;
    }

    public UserId getId() {
        return id;
    }

    public boolean getVisible() {
        return isVisible;
    }

    public HashedPassword getPassword() {
        return password;
    }

    public Email getEmail() {
        return email;
    }

    public void deActivateUser() {
        this.isVisible = false;
    }
}