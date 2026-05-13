package com.petshop.catalog.infrastructure.ratelimit;

import com.petshop.catalog.infrastructure.persistence.AbstractIntegrationTest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(
        webEnvironment =
                SpringBootTest.WebEnvironment.RANDOM_PORT
)
class RateLimitIntegrationTest
        extends AbstractIntegrationTest {

    @Autowired
    private TestRestTemplate restTemplate;

    private ResponseEntity<String> request(String clientId) {
        HttpHeaders headers = new HttpHeaders();

        headers.set("X-Client-Id", clientId);

        HttpEntity<Void> entity =
                new HttpEntity<>(headers);

        return restTemplate.exchange(
                "/products",
                HttpMethod.GET,
                entity,
                String.class
        );
    }

    @Test
    void should_block_requests_when_limit_exceeded() {

        for (int i = 0; i < 5; i++) {

            assertEquals(
                    HttpStatus.OK,
                    request("user-1").getStatusCode()
            );
        }

        assertEquals(
                HttpStatus.TOO_MANY_REQUESTS,
                request("user-1").getStatusCode()
        );
    }

    @Test
    void should_apply_rate_limit_per_client() {

        for (int i = 0; i < 6; i++) {
            request("user-1");
        }

        assertEquals(
                HttpStatus.OK,
                request("user-2").getStatusCode()
        );
    }
}