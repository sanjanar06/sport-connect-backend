package com.backend.sportC.service;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.backend.sportC.communication.Request.AuthRequest;
import com.backend.sportC.communication.Request.JWTRefreshRequest;
import com.backend.sportC.communication.Request.RegisterRequest;
import com.backend.sportC.communication.Response.JWTResponse;
import com.backend.sportC.config.JwtService;
import com.backend.sportC.entity.Role;
import com.backend.sportC.entity.User;
import com.backend.sportC.exception.UserNotFoundException;
import com.backend.sportC.repository.RoleRepository;
import com.backend.sportC.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public void register(RegisterRequest request) {
        Role role = roleRepository.findByType("USER")
                .orElseThrow(() -> new RuntimeException("Role not found: " + "USER"));
        var user = User.builder()
                .username(request.getUsername())
                .password(passwordEncoder.encode(request.getPassword()))
                .email(request.getEmail())
                .role(role)
                .build();

        userRepository.save(user);
    }

    public JWTResponse authenticate(AuthRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getUsername(),
                        request.getPassword()));
        User user = userRepository.findByUsername(request.getUsername())
                .orElseThrow(() -> new UserNotFoundException(request.getUsername()));

        return authResponse(user);
    }

    public JWTResponse refresh(JWTRefreshRequest request) {
        String refreshToken = request.getRefreshToken();
        String username = jwtService.extractUsername(refreshToken);

        if (username != null) {
            User user = userRepository.findByUsername(username).orElseThrow();

            if (jwtService.isTokenValid(refreshToken, user)) {
                return authRefreshResponse(user, refreshToken);
            }
        }

        return null;
    }

    private JWTResponse authResponse(User user) {
        String jwtRefreshToken = jwtService.generateRefreshToken(user);

        return authRefreshResponse(user, jwtRefreshToken);
    }

    private JWTResponse authRefreshResponse(User user, String refreshToken) {
        String jwtToken = jwtService.generateToken(user);

        return JWTResponse.builder()
                .accessToken(jwtToken)
                .refreshToken(refreshToken)
                .userRole(user.getRole())
                .build();
    }

}
