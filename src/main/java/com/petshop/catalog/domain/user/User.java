package com.petshop.catalog.domain.user;

import com.petshop.catalog.domain.shared.Email;

import java.util.UUID;

public class User{

    private UserId id;
    private Email email;
    private String password;
    private boolean isVisible;
    private Role role;

    private User(
            UUID id,
            String email,
            String password,
            boolean active,
            Role role
    ) {
        this.id = new UserId(id);
        this.email = new Email(email);
        this.changePassword(password);
        this.isVisible = active;
        this.setRole(role);
    }

    public static User create(String email, String password) {
        final boolean isVisible = true;
        final UUID id = UUID.randomUUID();
        final Role role = Role.USER;

        return new User(id, email, password, isVisible, role);
    }

    public static User rehydrate(
            UUID id,
            String email,
            String password,
            boolean isVisible,
            Role role
    ) {
        return new User(id, email, password, isVisible, role);
    }

    public void changePassword(String newPassword) {
        this.password = HashedPassword.fromString(newPassword);
    }

    public UserId getId() {
        return id;
    }

    public boolean getVisible() {
        return isVisible;
    }

    public String getPassword() {
        return password;
    }

    public Email getEmail() {
        return email;
    }

    public void deActivateUser() {
        this.isVisible = false;
    }

    private void setVisible(boolean isVisible) {
        this.isVisible = isVisible;
    }

    public void update(String email, String password, boolean isVisible) {
        this.email = new Email(email);
        this.changePassword(password);
        this.setVisible(isVisible);
    }

    public Role getRole() {
        return this.role;
    }

    private void setRole(Role role) {
        this.role = role;
    }
}