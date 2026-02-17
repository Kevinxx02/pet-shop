package com.petshop.catalog.application.user;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.petshop.catalog.domain.product.events.ProductCreated;
import com.petshop.catalog.domain.user.User;
import com.petshop.catalog.domain.user.UserRepository;
import com.petshop.catalog.infrastructure.persistence.outbox.OutboxMessage;
import com.petshop.catalog.infrastructure.persistence.outbox.OutboxRepository;
import com.petshop.catalog.infrastructure.persistence.product.ProductCreatedPayload;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.UUID;

@Service
@Transactional
public class CreateUserService {
    private final UserRepository userRepository;
    private final OutboxRepository outboxRepository;

    public CreateUserService(UserRepository userRepository,
                                OutboxRepository outboxRepository) {
        this.userRepository = userRepository;
        this.outboxRepository = outboxRepository;
    }

    @Transactional
    public UUID createUser(String name, String password) throws IOException {
        User user = User.create(name, password);

        userRepository.save(user);


        user.pullDomainEvents().forEach(event -> {
            if (event instanceof ProductCreated pc) {
                try {
                    ObjectMapper mapper = new ObjectMapper();
                    String payload = mapper.writeValueAsString(
                            ProductCreatedPayload.create(pc.getProductId())
                    );

                    OutboxMessage outboxMessage = OutboxMessage.create("Product created", payload);
                    outboxRepository.save(outboxMessage);
                } catch (JsonProcessingException e) {
                    throw new RuntimeException(e);
                }
            }
        });

        return user.getId();
    }
}