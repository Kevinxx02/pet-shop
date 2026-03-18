package com.petshop.catalog.infrastructure.persistence.cartitem;

import com.petshop.catalog.domain.cartitem.CartItem;
import com.petshop.catalog.domain.product.ProductPrice;
import com.petshop.catalog.infrastructure.persistence.cart.CartJpaEntity;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.UUID;

@Entity
@Table(name = "cart_item")
public class CartItemJpaEntity {

    @Id
    private UUID id;
    private UUID productId;
    private int quantity;
    private BigDecimal unitPrice;

    @ManyToOne
    @JoinColumn(name = "cart_id")
    private CartJpaEntity cart;

    public CartJpaEntity getCart() {
        return cart;
    }

    public void setCart(CartJpaEntity cart) {
        this.cart = cart;
    }

    public CartItemJpaEntity() {}

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public void setProductId(UUID productId) {
        this.productId = productId;
    }

    public UUID getProductId() {
        return productId;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setUnitPrice(BigDecimal unitPrice) {
        this.unitPrice = unitPrice;
    }

    public BigDecimal getUnitPrice() {
        return unitPrice;
    }

    public CartItem toDomain() {
        return CartItem.rehydrate(
                this.id,
                this.productId,
                this.quantity,
                this.unitPrice
        );
    }

    public static CartItemJpaEntity toEntity(CartItem cartItem) {
        CartItemJpaEntity entity = new CartItemJpaEntity();
        entity.setId(cartItem.getId());
        entity.setProductId(cartItem.getProductId());
        entity.setQuantity(cartItem.getQuantity());
        entity.setUnitPrice(cartItem.getUnitPrice());

        return entity;
    }
}