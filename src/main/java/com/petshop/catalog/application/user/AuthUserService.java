package com.petshop.catalog.application.user;

import com.petshop.catalog.infrastructure.security.JwtService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthUserService {

    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;

    public AuthUserService(
            AuthenticationManager authenticationManager,
            JwtService jwtService
    ) {
        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
    }

    public AuthResponse login(String email, String password) {
        Authentication auth = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(email, password)
        );

        List<String> roles = auth.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .toList();

        String accessToken = jwtService.generateToken(auth.getName(), roles);

        String refreshToken = jwtService.generateRefreshToken(auth.getName());

        return new AuthResponse(accessToken, refreshToken);
    }
}