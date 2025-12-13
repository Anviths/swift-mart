package com.ecom.controller;

import com.ecom.dto.AuthResponse;
import com.ecom.dto.RegisterRequest;
import com.ecom.service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/ecom/user/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<AuthResponse> registerUser( @Valid @RequestBody RegisterRequest registerRequest){
       AuthResponse authResponse =authService.registerUser(registerRequest);

      return ResponseEntity.status(HttpStatus.CREATED).body(authResponse);
    }
}
