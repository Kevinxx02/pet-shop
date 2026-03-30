package com.petshop.catalog.infrastructure.persistence.cartitem;

import com.petshop.catalog.application.cartItem.CartItemView;
import com.petshop.catalog.domain.cartitem.CartItemReadRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public class JpaCartItemReadRepository implements CartItemReadRepository {

    private final SpringDataCartItemReadRepository jpaRepository;

    public JpaCartItemReadRepository(SpringDataCartItemReadRepository jpaRepository) {
        this.jpaRepository = jpaRepository;
    }

    @Override
    public List<CartItemView> findByCartId(UUID cartId) {
        return jpaRepository.findByCartId(cartId);
    }
}
