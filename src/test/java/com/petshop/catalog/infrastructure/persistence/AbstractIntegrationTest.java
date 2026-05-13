package com.petshop.catalog.infrastructure.persistence;

import org.junit.jupiter.api.BeforeAll;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.containers.RabbitMQContainer;
import org.testcontainers.containers.wait.strategy.Wait;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.kafka.KafkaContainer;

@Testcontainers(disabledWithoutDocker = true)
@SpringBootTest
public abstract class AbstractIntegrationTest {
    @BeforeAll
    static void debugContainer() {
        System.out.println("JDBC URL: " + postgres.getJdbcUrl());
        System.out.println("Container running: " + postgres.isRunning());
    }
    @Container
    static PostgreSQLContainer<?> postgres =
            new PostgreSQLContainer<>("postgres:16")
                    .withDatabaseName("testdb")
                    .withUsername("test")
                    .withPassword("test")
                    .waitingFor(Wait.forListeningPort());

    @Container
    static RabbitMQContainer rabbit =
            new RabbitMQContainer("rabbitmq:3-management");

    @Container
    static KafkaContainer kafka =
            new KafkaContainer("apache/kafka:3.7.0");

    @DynamicPropertySource
    static void configureProperties(DynamicPropertyRegistry registry) {

        registry.add(
                "spring.datasource.url",
                postgres::getJdbcUrl
        );

        registry.add(
                "spring.datasource.username",
                postgres::getUsername
        );

        registry.add(
                "spring.datasource.password",
                postgres::getPassword
        );

        registry.add(
                "spring.rabbitmq.host",
                rabbit::getHost
        );

        registry.add(
                "spring.rabbitmq.port",
                rabbit::getAmqpPort
        );

        registry.add(
                "spring.rabbitmq.username",
                rabbit::getAdminUsername
        );

        registry.add(
                "spring.rabbitmq.password",
                rabbit::getAdminPassword
        );

        registry.add(
                "spring.kafka.bootstrap-servers",
                kafka::getBootstrapServers
        );
    }
}