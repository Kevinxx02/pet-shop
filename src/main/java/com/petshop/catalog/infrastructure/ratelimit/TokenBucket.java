package com.petshop.catalog.infrastructure.ratelimit;

public class TokenBucket {

    private final int capacity;
    private final long refillIntervalMillis;

    private int tokens;
    private long lastRefillTimestamp;

    public TokenBucket(int capacity, long refillIntervalMillis) {
        this.capacity = capacity;
        this.refillIntervalMillis = refillIntervalMillis;

        this.tokens = capacity;
        this.lastRefillTimestamp = System.currentTimeMillis();
    }

    public synchronized boolean tryConsume() {

        refill();

        if (tokens > 0) {
            tokens--;
            return true;
        }

        return false;
    }

    private void refill() {

        long now = System.currentTimeMillis();

        if (now - lastRefillTimestamp >= refillIntervalMillis) {

            this.tokens = capacity;

            this.lastRefillTimestamp = now;
        }
    }
}