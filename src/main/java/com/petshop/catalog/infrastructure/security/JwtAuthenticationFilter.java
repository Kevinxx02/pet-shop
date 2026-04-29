package com.petshop.catalog.infrastructure.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;

public class JwtAuthenticationFilter extends OncePerRequestFilter {
    private static final String BEARER_PREFIX = "Bearer ";
    private final JwtService jwtService;

    public JwtAuthenticationFilter(JwtService jwtService) {
        this.jwtService = jwtService;
    }

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain
    ) throws ServletException, IOException {

        final String authHeader = request.getHeader("Authorization");

        // 1. Validar header
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            return;
        }

        // 2. Extraer token
        String token = authHeader.substring(BEARER_PREFIX.length());

        // 3. Validar token
        if (!jwtService.isValid(token)) {
            filterChain.doFilter(request, response);
            return;
        }

        // 4. Obtener usuario (username/email)
        String username = jwtService.extractUsername(token);
        List<String> roles = jwtService.extractRoles(token);

        // 6. Convertir a authorities
        List<GrantedAuthority> authorities =
                roles.stream()
                        .map(role -> (GrantedAuthority) new SimpleGrantedAuthority(role))
                        .toList();

        // 7. Crear autenticación (sin roles aún)
        UsernamePasswordAuthenticationToken authToken =
                new UsernamePasswordAuthenticationToken(
                        username,
                        null,
                        authorities
                );

        authToken.setDetails(
                new WebAuthenticationDetailsSource().buildDetails(request)
        );

        // 8. Guardar en contexto
        SecurityContextHolder.getContext().setAuthentication(authToken);

        // continuar flujo
        filterChain.doFilter(request, response);
    }
}