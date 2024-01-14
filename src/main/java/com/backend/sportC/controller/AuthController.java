package com.backend.sportC.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.backend.sportC.communication.Request.AuthRequest;
import com.backend.sportC.communication.Request.JWTRefreshRequest;
import com.backend.sportC.communication.Request.RegisterRequest;
import com.backend.sportC.communication.Response.JWTResponse;
import com.backend.sportC.service.AuthService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    @PostMapping("/register")
    public void register(@RequestBody RegisterRequest request) {
        authService.register(request);
    }

    @PostMapping("/authenticate")
    public ResponseEntity<JWTResponse> authenticate(@RequestBody AuthRequest request) {
        return ResponseEntity.ok(authService.authenticate(request));
    }

    @PostMapping("/refresh")
    public ResponseEntity<JWTResponse> refresh(@RequestBody JWTRefreshRequest request) {
        return ResponseEntity.ok(authService.refresh(request));
    }

}
