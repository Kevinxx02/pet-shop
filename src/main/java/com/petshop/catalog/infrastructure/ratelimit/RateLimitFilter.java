package com.petshop.catalog.infrastructure.ratelimit;

import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.io.IOException;

import static net.logstash.logback.argument.StructuredArguments.kv;

@Component
@Slf4j
public class RateLimitFilter implements Filter {
    private final RateLimiterService rateLimiterService;
    private final Counter allowedRequests;
    private final Counter blockedRequests;

    public RateLimitFilter(
            RateLimiterService rateLimiterService,
            MeterRegistry registry
    ) {
        this.rateLimiterService = rateLimiterService;

        this.allowedRequests =
                registry.counter(
                        "ratelimit.allowed"
                );

        this.blockedRequests =
                registry.counter(
                        "ratelimit.blocked"
                );
    }

    @Override
    public void doFilter(
            ServletRequest request,
            ServletResponse response,
            FilterChain chain
    ) throws IOException, ServletException {

        HttpServletRequest httpRequest =
                (HttpServletRequest) request;

        HttpServletResponse httpResponse =
                (HttpServletResponse) response;

        String clientId =
                httpRequest.getHeader("X-Client-Id");

        if (clientId == null || clientId.isBlank()) {
            clientId = httpRequest.getRemoteAddr();
        }

        boolean allowed =
                rateLimiterService.allowRequest(clientId);

        if (!allowed) {
            blockedRequests.increment();
            log.warn("rate limit exceeded",
                    kv("client", clientId),
                    kv("path", httpRequest.getRequestURI()),
                    kv("method", httpRequest.getMethod())
            );

            httpResponse.setStatus(429);

            httpResponse.getWriter()
                    .write("Too many requests");

            return;
        }
        allowedRequests.increment();
        log.info("request allowed",
                kv("client", clientId),
                kv("path", httpRequest.getRequestURI()),
                kv("method", httpRequest.getMethod())
        );

        chain.doFilter(request, response);
    }
}