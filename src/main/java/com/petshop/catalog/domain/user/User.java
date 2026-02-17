package com.petshop.catalog.domain.user;

import com.petshop.catalog.domain.DomainEvent;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class User {
    private UUID id;
    private String name;
    private String password;

    private final List<DomainEvent> domainEvents = new ArrayList<>();

    private User(UUID id, String name, String password) {
        this.id = id;
        this.name = name;
        this.password = password;
    }

    public static User create(String name, String password) {
        final UUID id = UUID.randomUUID();
        return new User(id, name, password);
    }

    public List<DomainEvent> pullDomainEvents() {
        List<DomainEvent> events = new ArrayList<>(domainEvents);
        domainEvents.clear();
        return events;
    }

    public UUID getId() {
        return id;
    }
    public String getName() {
        return this.name;
    }
    public String getPassword() {
        return this.password;
    }
}
