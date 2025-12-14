package com.ecom.controller;

import com.ecom.dto.UserDto;
import com.ecom.entity.User;
import com.ecom.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/ecom/user")
@RequiredArgsConstructor
public class UserController {

    private  final UserService userService;
    @GetMapping("/me")
    public ResponseEntity<UserDto> me(){
        Authentication authentication =
                SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null || !authentication.isAuthenticated()) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
       String name= authentication.getName();
        System.out.println(name);
      User user= userService.loadUserByEmail(name);

       UserDto userDto=UserDto.builder()
               .id(user.getUserId())
               .name(user.getName())
               .phone(user.getPhone())
               .email(user.getEmail())
               .roles(user.getRoles())
               .createAt(user.getCreatedAt())
               .build();
        return ResponseEntity.ok(userDto);


    }
}
