package com.petshop.catalog.infrastructure.persistence.cartitem;

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
    @Column(name = "cart_id", nullable = false)
    private UUID cartId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cart_id", insertable = false, updatable = false)
    private CartJpaEntity cart;



    protected CartItemJpaEntity() {}
    public CartItemJpaEntity(UUID id, UUID cartId, UUID productId, int quantity, BigDecimal unitPrice) {
        this.id = id;
        this.cartId = cartId;
        this.productId = productId;
        this.quantity = quantity;
        this.unitPrice = unitPrice;
    }

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

    public void setCartId(UUID cartId) {
        this.cartId = cartId;
    }

    public UUID getCartId() {
        return cartId;
    }
}