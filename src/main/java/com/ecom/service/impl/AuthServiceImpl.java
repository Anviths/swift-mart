package com.ecom.service.impl;

import com.ecom.dto.AuthResponse;
import com.ecom.dto.LoginRequest;
import com.ecom.dto.RegisterRequest;
import com.ecom.entity.RefreshToken;
import com.ecom.entity.User;
import com.ecom.exception.InvalidCredentialsException;
import com.ecom.exception.UserNotFoundException;
import com.ecom.security.JwtService;
import com.ecom.service.AuthService;
import com.ecom.service.RefreshTokenService;
import com.ecom.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Service;

import java.util.HashSet;


@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserService userService;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final RefreshTokenService refreshTokenService;

    @Override
    public AuthResponse registerUser(RegisterRequest request) {
        User user=userService.createUser(request);
        String token=jwtService.generateToken(user);
        RefreshToken refreshToken=refreshTokenService.createRefreshToken(user);
        return buildResponse(user,token,refreshToken.getToken());
    }

    @Override
    public AuthResponse login(LoginRequest request) {
        try{
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getEmail(),request.getPassword())
        );} catch (AuthenticationException e) {
            throw new InvalidCredentialsException();
        }
        User user=userService.loadUserByEmail(request.getEmail());
        String token =jwtService.generateToken(user);
        RefreshToken refreshToken=refreshTokenService.createRefreshToken(user);
        return buildResponse(user,token,refreshToken.getToken());
    }

    @Override
    public AuthResponse refreshToken(String refreshToken) {
        RefreshToken rt=refreshTokenService.verifyAndGet(refreshToken);
        User user=rt.getUser();
        String acessToken= jwtService.generateToken(user);
        return buildResponse(user,acessToken,rt.getToken());
    }

    @Override
    public void logout(String refreshToken) {
        refreshTokenService.revoke(refreshToken);
    }
    private AuthResponse buildResponse(User user, String accessToken, String refreshToken) {
        return AuthResponse.builder()
                .token(accessToken)
                .refreshToken(refreshToken)
                .email(user.getEmail())
                .name(user.getName())
                .roles(new HashSet<>(user.getRoles()))
                .build();
    }
}
