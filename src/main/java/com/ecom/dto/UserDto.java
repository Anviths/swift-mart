package com.ecom.dto;

import com.ecom.entity.Role;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Set;

@Data
@Builder
public class UserDto {

    private Long id;
    private String name;
    private String email;
    private String phone;
    private Set<Role> roles;
    private LocalDateTime createAt;

}
