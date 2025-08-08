package com.usk.ecommerce.service;

import com.usk.ecommerce.dto.UserRegistrationRequest;
import com.usk.ecommerce.entity.User;

public interface UserService {
    User registerUser(UserRegistrationRequest registrationRequest);
    User login(String email, String password);
}
