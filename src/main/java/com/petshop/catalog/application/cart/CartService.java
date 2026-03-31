package com.petshop.catalog.application.cart;

import com.petshop.catalog.application.status.StatusService;
import com.petshop.catalog.domain.cart.Cart;
import com.petshop.catalog.domain.cart.CartReadRepository;
import com.petshop.catalog.domain.cart.CartRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@Transactional
public class CartService {

    private final CartRepository cartRepository;
    private final CartReadRepository cartReadRepository;
    private final StatusService statusService;
    final private UUID pendingId;

    public CartService(CartRepository cartRepository,
                       CartReadRepository cartReadRepository,
                       StatusService statusService
    ) {
        this.cartRepository = cartRepository;
        this.cartReadRepository = cartReadRepository;
        this.statusService = statusService;
        this.pendingId = this.statusService.getPendingId();
    }

    public List<CartView> findAll() {
        return this.cartReadRepository.viewAll();
    }

    public CartView create() {
        final Cart cart = Cart.create(this.pendingId);

        this.cartRepository.save(cart);

        return CartMapper.toView(cart);
    }

    /* Funcionaria mejor con una maquina de estados, pero al menos con el checkAvailability, me aseguro que tenga el estado inicial, seria incomodo si esta en estado pagado y se pasa a estado abandonado, no de estado abandonado a estado pagado */
    public CartView updateStatus(UUID id, UUID statusId) {
        final Cart cart = this.cartRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Carrito no encontrado"));

        if (!cart.getStatusId().equals(this.pendingId)) {
            throw new IllegalArgumentException("El carrito no esta disponible");
        }

        if (!this.statusService.existsById(statusId)) {
            throw new IllegalArgumentException("El estado no existe");
        }

        cart.updateStatus(statusId);

        this.cartRepository.save(cart);

        return CartMapper.toView(cart);
    }

    public boolean checkAvailability(UUID id) {
        return this.cartRepository.existsByIdAndStatusId(id, this.pendingId);
    }
}