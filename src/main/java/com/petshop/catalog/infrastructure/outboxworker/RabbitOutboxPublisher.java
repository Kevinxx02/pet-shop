package com.petshop.catalog.infrastructure.outboxworker;

import com.petshop.catalog.infrastructure.rabbitmq.RabbitConfig;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

@Component
public class RabbitOutboxPublisher implements OutboxPublisher {

    private final RabbitTemplate rabbitTemplate;

    public RabbitOutboxPublisher(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    @Override
    public void publish(String eventType, String payload) {

        String routingKey = mapRoutingKey(eventType);

        rabbitTemplate.convertAndSend(
                RabbitConfig.EXCHANGE,
                routingKey,
                payload
        );
    }

    private String mapRoutingKey(String eventType) {
        if ("Product created".equals(eventType)) {
            return RabbitConfig.ROUTING_KEY;
        }
        return "unknown";
    }
}

