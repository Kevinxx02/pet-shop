package com.petshop.catalog.infrastructure.ratelimit;

import org.springframework.stereotype.Service;

import java.util.concurrent.ConcurrentHashMap;

@Service
public class RateLimiterService {
    final int NUM_MAX_REQUESTS = 5;
    final int NUM_MAX_MILLISECONDS = 30000;

    private final ConcurrentHashMap<String, TokenBucket> buckets =
            new ConcurrentHashMap<>();

    public boolean allowRequest(String ip) {

        TokenBucket bucket = buckets.computeIfAbsent(
                ip,
                key -> new TokenBucket(NUM_MAX_REQUESTS, NUM_MAX_MILLISECONDS)
        );

        return bucket.tryConsume();
    }
}