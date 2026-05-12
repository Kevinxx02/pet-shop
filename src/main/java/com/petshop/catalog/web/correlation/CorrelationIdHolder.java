package com.petshop.catalog.web.correlation;

import org.springframework.stereotype.Component;

@Component
public class CorrelationIdHolder {

    private static final ThreadLocal<String> CONTEXT = new ThreadLocal<>();

    public void set(String id) {
        CONTEXT.set(id);
    }

    public String get() {
        return CONTEXT.get();
    }

    public void clear() {
        CONTEXT.remove();
    }
}