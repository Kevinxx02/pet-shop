package com.petshop.catalog.infrastructure.rabbitmq;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class ProductEventListener {

    @RabbitListener(queues = RabbitConfig.QUEUE)
    public void handle(String payload) {
        log.info("Rabbit MQ event received: {}", payload);
    }
}