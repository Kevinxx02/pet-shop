package com.petshop.catalog.domain.cart;

import java.util.Optional;
import java.util.UUID;

public interface CartRepository {
    void save(Cart cart);

    Optional<Cart> findById(UUID id);

    boolean existsById(UUID id);

    boolean existsByIdAndStatusId(UUID id, UUID statusID);
}