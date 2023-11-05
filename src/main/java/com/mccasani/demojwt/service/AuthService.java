package com.mccasani.demojwt.service;

import com.mccasani.demojwt.controller.Auth.model.AuthResponse;
import com.mccasani.demojwt.controller.Auth.model.LoginRequest;
import com.mccasani.demojwt.controller.Auth.model.RegisterRequest;
import com.mccasani.demojwt.Jwt.JwtService;
import com.mccasani.demojwt.entity.Role;
import com.mccasani.demojwt.entity.UserEntity;
import com.mccasani.demojwt.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    public AuthResponse login(LoginRequest request) {
        this.authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
        UserDetails user=this.userRepository.findByUsername(request.getUsername()).orElseThrow();
        String token=this.jwtService.getToken(user);
        return AuthResponse.builder()
            .token(token)
            .build();

    }

    public void register(RegisterRequest request) {
        UserEntity userEntity = UserEntity.builder()
            .username(request.getUsername())
            .password(this.passwordEncoder.encode( request.getPassword()))
            .firstname(request.getFirstname())
            .lastname(request.getLastname())
            .country(request.getCountry())
            .role(Role.USER)
            .build();

        userRepository.save(userEntity);
    }

}
