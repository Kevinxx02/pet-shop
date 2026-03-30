package com.petshop.catalog.domain.cartitem;

import java.util.Optional;
import java.util.UUID;

public interface CartItemRepository {
    void save(CartItem item);

    Optional<CartItem> findByCartIdAndProductId(UUID cartId, UUID productId);

    boolean existsByCartIdAndProductId(UUID cartId, UUID productId);

    Optional<CartItem> findById(UUID id);

    void deleteById(UUID id);

    boolean existsById(UUID id);
}
