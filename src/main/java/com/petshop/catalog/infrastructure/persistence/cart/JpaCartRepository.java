package com.petshop.catalog.infrastructure.persistence.cart;

import com.petshop.catalog.domain.cart.Cart;
import com.petshop.catalog.domain.cart.CartRepository;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class JpaCartRepository implements CartRepository {

    private final SpringDataCartRepository jpaRepository;

    public JpaCartRepository(SpringDataCartRepository jpaRepository) {
        this.jpaRepository = jpaRepository;
    }

    @Override
    public void save(Cart cart) {
        this.jpaRepository.save(CartMapper.toEntity(cart));
    }

    @Override
    public Optional<Cart> findById(UUID id) {
        return this.jpaRepository.findById(id)
                .map(CartMapper::toDomain);
    }

    @Override
    public boolean existsById(UUID id) {
        return this.jpaRepository.existsById(id);
    }

    @Override
    public boolean existsByIdAndStatusId(UUID id, UUID statusID) {
        return this.jpaRepository.existsByIdAndStatusId(id, statusID);
    }
}
