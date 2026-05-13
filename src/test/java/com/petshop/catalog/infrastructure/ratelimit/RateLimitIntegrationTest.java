package com.petshop.catalog.infrastructure.ratelimit;

import com.petshop.catalog.infrastructure.persistence.AbstractIntegrationTest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(
        webEnvironment =
                SpringBootTest.WebEnvironment.RANDOM_PORT
)
class RateLimitIntegrationTest
        extends AbstractIntegrationTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @LocalServerPort
    private int port;

    @Test
    void should_block_requests_when_rate_limit_exceeded() {

        String url =
                "http://localhost:" + port + "/products";

        ResponseEntity<String> response = null;

        for (int i = 0; i < 5; i++) {

            response =
                    restTemplate.getForEntity(
                            url,
                            String.class
                    );

            /* Valida que las primeras 5 solicitudes obtengan OK */
            assertEquals(
                    HttpStatus.OK,
                    response.getStatusCode()
            );
        }

        response =
                restTemplate.getForEntity(
                        url,
                        String.class
                );

        /* Valida que la sexta solicitud reciba too many requests */
        assertEquals(
                HttpStatus.TOO_MANY_REQUESTS,
                response.getStatusCode()
        );
    }
}