package com.ecom.service.impl;

import com.ecom.dto.AuthResponse;
import com.ecom.dto.LoginRequest;
import com.ecom.dto.RegisterRequest;
import com.ecom.entity.Role;
import com.ecom.entity.User;
import com.ecom.security.JwtService;
import com.ecom.service.AuthService;
import com.ecom.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserService userService;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;


    @Override
    public AuthResponse registerUser(RegisterRequest request) {
        User user=userService.createUser(request);
        String token=jwtService.generateToken(user);
        return AuthResponse.builder()
                .token(token)
                .email(user.getEmail())
                .name(user.getName())
                .roles(user.getRoles().stream().collect(Collectors.toSet()))
                .build();
    }

    @Override
    public AuthResponse login(LoginRequest request) {
        return null;
    }
}
