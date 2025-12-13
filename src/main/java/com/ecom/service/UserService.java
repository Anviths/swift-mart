package com.ecom.service;

import com.ecom.dto.RegisterRequest;
import com.ecom.entity.User;

public interface UserService {
    User createUser(RegisterRequest request);
    User loadUserByEmail(String email);

}
