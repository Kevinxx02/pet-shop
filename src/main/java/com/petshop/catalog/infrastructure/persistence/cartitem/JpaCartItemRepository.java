package com.petshop.catalog.infrastructure.persistence.cartitem;

import com.petshop.catalog.domain.cartitem.CartItem;
import com.petshop.catalog.domain.cartitem.CartItemRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public class JpaCartItemRepository implements CartItemRepository {

    private final SpringDataCartItemRepository jpaRepository;

    public JpaCartItemRepository(SpringDataCartItemRepository jpaRepository) {
        this.jpaRepository = jpaRepository;
    }

    @Override
    public void save(CartItem item) {
        this.jpaRepository.save(CartItemMapper.toEntity(item));
    }

    @Override
    public Optional<CartItem> findByCartIdAndProductId(UUID cartId, UUID productId) {
        return this.jpaRepository.findByCartIdAndProductId(cartId, productId);
    }

    @Override
    public boolean existsByCartIdAndProductId(UUID cartId, UUID productId) {
        return this.jpaRepository.existsByCartIdAndProductId(cartId, productId);
    }

    @Override
    public Optional<CartItem> findById(UUID id) {
        return this.jpaRepository.findById(id)
                .map(CartItemMapper::toDomain);
    }

    @Override
    public void deleteById(UUID id) {
        this.jpaRepository.deleteById(id);
    }

    @Override
    public boolean existsById(UUID id) {
        return this.jpaRepository.existsById(id);
    }
}
