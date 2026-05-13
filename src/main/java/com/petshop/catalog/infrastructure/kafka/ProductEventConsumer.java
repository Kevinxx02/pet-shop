package com.petshop.catalog.infrastructure.kafka;

import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class ProductEventConsumer {

    @KafkaListener(topics = "product-events", groupId = "product-group")
    public void consume(String message) {
        log.info("Kafka event received: {}", message);
    }
}