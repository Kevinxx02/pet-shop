package com.petshop.catalog.infrastructure.security;

import com.petshop.catalog.domain.shared.Email;
import com.petshop.catalog.domain.user.User;
import com.petshop.catalog.domain.user.UserReadRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UserReadRepository userRepository;

    public CustomUserDetailsService(UserReadRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(new Email(email))
                .orElseThrow(() -> new IllegalArgumentException("Invalid credentials"));

        return org.springframework.security.core.userdetails.User
                .builder()
                .username(user.getEmail().toString())
                .password(user.getPassword())
                .authorities("ROLE_" + user.getRole().name())
                .build();
    }
}