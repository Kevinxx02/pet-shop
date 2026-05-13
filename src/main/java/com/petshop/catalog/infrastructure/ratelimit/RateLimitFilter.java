package com.petshop.catalog.infrastructure.ratelimit;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class RateLimitFilter implements Filter {

    private final RateLimiterService rateLimiterService;

    public RateLimitFilter(RateLimiterService rateLimiterService) {
        this.rateLimiterService = rateLimiterService;
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

        String ip = httpRequest.getRemoteAddr();

        boolean allowed =
                rateLimiterService.allowRequest(ip);

        if (!allowed) {

            httpResponse.setStatus(429);

            httpResponse.getWriter()
                    .write("Too many requests");

            return;
        }

        chain.doFilter(request, response);
    }
}