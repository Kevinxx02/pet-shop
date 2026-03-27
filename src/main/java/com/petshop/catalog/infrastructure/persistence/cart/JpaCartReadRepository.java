package com.petshop.catalog.infrastructure.persistence.cart;

import com.petshop.catalog.application.cart.CartView;
import com.petshop.catalog.domain.cart.CartReadRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class JpaCartReadRepository implements CartReadRepository {

    private final SpringDataCartReadRepository jpaRepository;

    public JpaCartReadRepository(SpringDataCartReadRepository jpaRepository) {
        this.jpaRepository = jpaRepository;
    }

    @Override
    public List<CartView> viewAll() {
        return this.jpaRepository.viewAll();
    }
}
