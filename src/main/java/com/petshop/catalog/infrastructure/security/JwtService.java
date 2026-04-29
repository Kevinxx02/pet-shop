package com.petshop.catalog.infrastructure.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;

import javax.crypto.SecretKey;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class JwtService {
    private final Long REFRESH_EXPIRATION = 90000L;

    /* Deuda tecnica */
    private final SecretKey key = Keys.hmacShaKeyFor(
            "my-secret-key-my-secret-key-123456".getBytes()
    );

    private final long EXPIRATION = 1000 * 60 * 60; // 1 hora

    public String generateToken(String username, List<String> roles) {
        return Jwts.builder()
                .subject(username)
                .claim("roles", roles)
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + EXPIRATION))
                .signWith(key)
                .compact();
    }

    public String extractUsername(String token) {
        return extractClaims(token).getSubject();
    }

    public List<String> extractRoles(String token) {
        return extractClaims(token).get("roles", List.class);
    }

    public boolean isValid(String token) {
        try {
            extractClaims(token);
            return true;
        } catch (JwtException e) {
            return false;
        }
    }

    private Claims extractClaims(String token) {
        return Jwts.parser()
                .verifyWith(key)
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    public String generateRefreshToken(String username) {
        return Jwts.builder()
                .subject(username)
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + REFRESH_EXPIRATION))
                .signWith(key)
                .compact();
    }
}