package com.petshop.catalog.domain;


import java.time.Instant;

public interface DomainEvent {

    Instant occurredOn();
}