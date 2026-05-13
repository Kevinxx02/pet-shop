package com.petshop.catalog.infrastructure.kafka;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import static net.logstash.logback.argument.StructuredArguments.kv;

@Component
@Slf4j
public class ProductEventConsumer {

    private final ObjectMapper objectMapper;
    private final Set<String> processed = ConcurrentHashMap.newKeySet();

    public ProductEventConsumer(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @KafkaListener(topics = "product-events", groupId = "product-group")
    public void consume(String message) {

        try {
            ProductCreatedEvent event =
                    objectMapper.readValue(message, ProductCreatedEvent.class);

            /* Idempotencia simple, cada vez agrega el evento a la lista de procesados
            * Si no puede agregarlo es porque ya existe, y si ya existe
            * no se debe volver a procesar */
            if (!processed.add(String.valueOf(event.productId()))) {
                return;
            }

            log.info("kafka event processed",
                    kv("eventType", "ProductCreated"),
                    kv("name", event.name())
            );

        } catch (Exception e) {

            log.error("kafka event parsing failed",
                    kv("payload", message),
                    kv("error", e.getMessage())
            );
        }
    }
}