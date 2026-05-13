package com.petshop.catalog.infrastructure.kafka;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class ProductEventProducer {

    private final KafkaTemplate<String, String> kafkaTemplate;

    public ProductEventProducer(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void publish(String event) {
        kafkaTemplate.send("product-events", event);
    }
}