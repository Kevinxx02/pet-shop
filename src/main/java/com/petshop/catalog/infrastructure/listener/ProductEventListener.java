package com.petshop.catalog.infrastructure.listener;

import com.petshop.catalog.infrastructure.rabbitmq.RabbitConfig;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class ProductEventListener {

    @RabbitListener(queues = RabbitConfig.QUEUE)
    public void handle(String payload) {
        System.out.println("Infrastructure/Listener: " + payload);
    }
}
