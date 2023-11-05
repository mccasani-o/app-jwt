package com.mccasani.demojwt.controller.Auth;

import com.mccasani.demojwt.controller.Auth.model.AuthResponse;
import com.mccasani.demojwt.controller.Auth.model.LoginRequest;
import com.mccasani.demojwt.controller.Auth.model.RegisterRequest;
import com.mccasani.demojwt.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping(value = "/login")
    public ResponseEntity<AuthResponse> login(@RequestBody LoginRequest request) {
        return ResponseEntity.ok(this.authService.login(request));
    }

    @PostMapping(value = "/register")
    public ResponseEntity<Void> register(@RequestBody RegisterRequest request) {
        this.authService.register(request);
        return new ResponseEntity<>(HttpStatus.CREATED);

    }
}
