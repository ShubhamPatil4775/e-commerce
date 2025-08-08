package com.usk.ecommerce.controller;

import com.usk.ecommerce.dto.UserLogin;
import com.usk.ecommerce.dto.UserRegistrationRequest;
import com.usk.ecommerce.entity.User;
import com.usk.ecommerce.exception.InvalidCredentials;
import com.usk.ecommerce.serviceimpl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;


@RestController
@RequestMapping("api/v1")
public class UserController {
    @Autowired
    private UserServiceImpl userService;

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@Valid @RequestBody UserRegistrationRequest request){
        return new ResponseEntity<>(userService.registerUser(request),HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody UserLogin login){
        User user = userService.login(login.getEmail(),login.getPassword());
        if (user ==null){
            throw new InvalidCredentials("Invalid credentials enter correct email and password");
        }else
            return new ResponseEntity<>(user,HttpStatus.OK);
    }

}

