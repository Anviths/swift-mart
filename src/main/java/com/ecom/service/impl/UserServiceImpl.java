package com.ecom.service.impl;

import com.ecom.dao.UserRepository;
import com.ecom.dto.RegisterRequest;
import com.ecom.entity.User;
import com.ecom.exception.AuthenticationException;
import com.ecom.exception.UserAlreadyExistsException;
import com.ecom.exception.UserNotFoundException;
import com.ecom.service.UserService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor

public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    @Transactional
    public User createUser(RegisterRequest request) {
        if(userRepository.existsByEmail(request.getEmail())){
            throw new UserAlreadyExistsException("already this username exist");
        }
        User user= User.builder()
                .name(request.getName())
                .email(request.getEmail())
                .phone(request.getPhone())
                .dateOfBirth(request.getDateOfBirth())
                .password(passwordEncoder.encode( request.getPassword()))
                .roles(request.getRole()).build();
        return userRepository.save(user);
    }

    @Override
    public User loadUserByEmail(String email) {

         return userRepository.findByEmail(email)
                .orElseThrow(() -> new UserNotFoundException("user not found "));
    }
}
