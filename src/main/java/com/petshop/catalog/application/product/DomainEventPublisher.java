package com.petshop.catalog.application.product;

import com.petshop.catalog.domain.DomainEvent;

public interface DomainEventPublisher {

    void publish(DomainEvent event);

}