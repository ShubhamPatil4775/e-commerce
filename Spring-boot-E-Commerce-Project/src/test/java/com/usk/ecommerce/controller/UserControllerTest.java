package com.usk.ecommerce.controller;

import com.usk.ecommerce.dto.UserLogin;
import com.usk.ecommerce.dto.UserRegistrationRequest;
import com.usk.ecommerce.entity.User;
import com.usk.ecommerce.service.UserService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
public class UserControllerTest {

    @InjectMocks
    private UserController userController;

    @Mock
    private UserService userService;

    private UserRegistrationRequest registrationRequest;
    private UserLogin userLogin;
    private User user;

    @BeforeEach
    void setUp() {
        registrationRequest = new UserRegistrationRequest();
        registrationRequest.setEmail("test@example.com");
        registrationRequest.setPassword("password123");
        registrationRequest.setFirstName("John");
        registrationRequest.setLastName("Doe");
        registrationRequest.setMobileNo(9876543210L);

        userLogin = new UserLogin();
        userLogin.setEmail("test@example.com");
        userLogin.setPassword("password123");

        user = new User("John", "Doe", "test@example.com", "password123", 9876543210L);

    }

    @Test
    void testRegisterUser_Success() {
        when(userService.registerUser(Mockito.any(UserRegistrationRequest.class)))
                .thenReturn(user);

        ResponseEntity<?> response = userController.registerUser(registrationRequest);

        Assertions.assertEquals(HttpStatus.CREATED, response.getStatusCode());
        Assertions.assertEquals("User registered Successfully!", response.getBody());
    }

    @Test
    void testLogin_Success() {
        when(userService.login(userLogin.getEmail(), userLogin.getPassword()))
                .thenReturn(user);

        ResponseEntity<?> response = userController.login(userLogin);

        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
        Assertions.assertEquals("User Login Successfully..!", response.getBody());
    }
}
