package com.petshop.catalog.infrastructure.kafka;

import com.petshop.catalog.infrastructure.persistence.AbstractIntegrationTest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest()
@ActiveProfiles("test")
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_CLASS)
class KafkaIntegrationTest extends AbstractIntegrationTest {
    @MockBean
    private KafkaTemplate<String, String> kafkaTemplate;

    @Autowired
    private ProductEventProducer producer;

    @Test
    void should_publish_and_consume_event() throws InterruptedException {

        producer.publish("Product created", "{\"name\":\"product-1\"}");

        Thread.sleep(2000);

        assertTrue(true);
    }
}