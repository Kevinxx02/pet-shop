package com.petshop.catalog.application.cartItem;

import com.petshop.catalog.application.status.StatusService;
import com.petshop.catalog.domain.cart.CartRepository;
import com.petshop.catalog.domain.cartitem.CartItem;
import com.petshop.catalog.domain.cartitem.CartItemReadRepository;
import com.petshop.catalog.domain.cartitem.CartItemRepository;
import com.petshop.catalog.domain.product.Product;
import com.petshop.catalog.domain.product.ProductRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Service
public class CartItemService {
    final CartRepository cartRepository;
    final ProductRepository productRepository;
    private final StatusService statusService;
    private final CartItemRepository cartItemRepository;
    private final CartItemReadRepository cartItemReadRepository;

    public CartItemService(
            CartRepository cartRepository,
            ProductRepository productRepository,
            StatusService statusService,
            CartItemRepository cartItemRepository,
            CartItemReadRepository cartItemReadRepository
    ) {
        this.cartRepository = cartRepository;
        this.productRepository = productRepository;
        this.statusService = statusService;
        this.cartItemRepository = cartItemRepository;
        this.cartItemReadRepository = cartItemReadRepository;
    }

    public CartItemView addItem(UUID cartId, UUID productId, int quantity) {
        if (!this.cartRepository.existsByIdAndStatusId(cartId, this.statusService.getPendingId())) {
            throw new IllegalArgumentException("Carrito no encontrado");
        }

        final Product product = this.productRepository.findById(productId)
                .orElseThrow(() -> new IllegalArgumentException("Producto no encontrado"));

        final BigDecimal unitPrice = product.getPrice().value();

        if (this.cartItemRepository.existsByCartIdAndProductId(cartId, productId)) {
            final CartItem item = this.cartItemRepository.findByCartIdAndProductId(cartId, productId)
                    .orElseThrow(() -> new IllegalArgumentException("Item no encontrado"));

            return this.updateItem(item.getId(), quantity);
        }

        final CartItem item = CartItem.create(cartId, productId, quantity, unitPrice);

        this.cartItemRepository.save(item);

        return CartItemMapper.toView(item);
    }

    public CartItemView updateItem(UUID id,  int quantity) {
        final CartItem item = this.cartItemRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Item no encontrado"));

        item.updateQuantity(quantity);

        this.cartItemRepository.save(item);

        return CartItemMapper.toView(item);
    }

    public void deleteItem(UUID id) {
        if (!this.cartItemRepository.existsById(id)) {
            throw new IllegalArgumentException("Item no encontrado");
        }

        this.cartItemRepository.deleteById(id);
    }

    public List<CartItemView> getItems(UUID cartId) {
        return this.cartItemReadRepository.findByCartId(cartId);
    }
}
