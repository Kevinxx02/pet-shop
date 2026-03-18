package com.petshop.catalog.infrastructure.persistence.cartitem;

import com.petshop.catalog.domain.cartitem.CartItem;
import com.petshop.catalog.domain.cartitem.CartItemRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public class JpaCartItemRepository implements CartItemRepository {

    private final SpringDataCartItemRepository jpaRepository;

    public JpaCartItemRepository(SpringDataCartItemRepository jpaRepository) {
        this.jpaRepository = jpaRepository;
    }

    @Override
    public void save(CartItemJpaEntity entity) {
        jpaRepository.save(entity);
    }

    @Override
    public List<CartItem> findAll() {
        return jpaRepository.findAll().stream().map(CartItemJpaEntity::toDomain).toList();
    }

    @Override
    public void deleteById(UUID id) {
        jpaRepository.deleteById(id);
    }

    @Override
    public Optional<CartItemJpaEntity> findById(UUID id) {
        return jpaRepository.findById(id);
    }
}
