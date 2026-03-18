package com.petshop.catalog.infrastructure.persistence.cart;

import com.petshop.catalog.domain.cart.Cart;
import com.petshop.catalog.domain.cart.CartRepository;
import com.petshop.catalog.domain.cartitem.CartItem;
import com.petshop.catalog.infrastructure.persistence.cartitem.CartItemJpaEntity;
import org.springframework.stereotype.Repository;

import java.util.*;
import java.util.stream.Collectors;

@Repository
public class JpaCartRepository implements CartRepository {

    private final SpringDataCartRepository jpaRepository;

    public JpaCartRepository(SpringDataCartRepository jpaRepository) {
        this.jpaRepository = jpaRepository;
    }

    @Override
    public void save(CartJpaEntity entity) {
        jpaRepository.save(entity);
    }

    @Override
    public CartJpaEntity toEntity(Cart cart, CartJpaEntity entity) {

        if (entity.getItems() == null) {
            entity.setItems(new ArrayList<>());
        }

        // INDEXAR existentes por ID
        Map<UUID, CartItemJpaEntity> existing = entity.getItems().stream()
                .collect(Collectors.toMap(CartItemJpaEntity::getId, i -> i));

        List<CartItemJpaEntity> updatedItems = new ArrayList<>();

        for (CartItem item : cart.getItems()) {

            CartItemJpaEntity jpaItem = existing.get(item.getId());

            if (jpaItem == null) {
                // NUEVO
                jpaItem = CartItemJpaEntity.toEntity(item);
                jpaItem.setCart(entity);
            } else {
                // ACTUALIZAR (sin recrear)
                jpaItem.setQuantity(item.getQuantity());
                jpaItem.setUnitPrice(item.getUnitPrice());
            }

            updatedItems.add(jpaItem);
        }

        // 🔴 REMOVE solo los que ya no existen
        entity.getItems().removeIf(existingItem ->
                updatedItems.stream().noneMatch(i -> i.getId().equals(existingItem.getId()))
        );

        // 🟢 ADD solo los nuevos
        for (CartItemJpaEntity item : updatedItems) {
            if (!entity.getItems().contains(item)) {
                entity.getItems().add(item);
            }
        }

        entity.setId(cart.getId());
        entity.setStatus(cart.getStatus());
        entity.setCreatedAt(cart.getCreatedAt());

        return entity;
    }

    @Override
    public Cart toDomain(CartJpaEntity entity) {
        List<CartItem> items = entity.getItems()
                .stream()
                .map(CartItemJpaEntity::toDomain)
                .toList();

        return Cart.rehydrate(entity.getId(), entity.getStatus(), items, entity.getCreatedAt());
    }

    @Override
    public List<Cart> findAll() {
        return jpaRepository.findAll().stream().map(this::toDomain).toList();
    }

    @Override
    public void deleteById(UUID id) {
        jpaRepository.deleteById(id);
    }

    @Override
    public Optional<CartJpaEntity> findById(UUID id) {
        return jpaRepository.findById(id);
    }

    @Override
    public CartJpaEntity findByIdAndStatus(UUID id, String status) {
        return jpaRepository.findByIdAndStatus(id, status);
    }
}
