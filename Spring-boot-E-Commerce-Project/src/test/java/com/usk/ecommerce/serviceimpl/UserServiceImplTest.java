package com.usk.ecommerce.serviceimpl;

import com.usk.ecommerce.dto.UserLogin;
import com.usk.ecommerce.dto.UserRegistrationRequest;
import com.usk.ecommerce.entity.User;
import com.usk.ecommerce.repository.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
public class UserServiceImplTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserServiceImpl userService;

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
        when(userRepository.findByEmail(registrationRequest.getEmail()))
                .thenReturn(Optional.empty());

        when(userRepository.save(Mockito.any(User.class)))
                .thenReturn(user);

        User result = userService.registerUser(registrationRequest);

        Assertions.assertNotNull(result);
        Assertions.assertEquals("test@example.com", result.getEmail());
        Mockito.verify(userRepository).save(Mockito.any(User.class));
    }

    @Test
    void testLogin_Success() {
        when(userRepository.findByEmailAndPassword(userLogin.getEmail(), userLogin.getPassword()))
                .thenReturn(Optional.of(user));

        User result = userService.login(userLogin.getEmail(), userLogin.getPassword());

        Assertions.assertNotNull(result);
        Assertions.assertEquals("test@example.com", result.getEmail());
        Mockito.verify(userRepository).findByEmailAndPassword(userLogin.getEmail(), userLogin.getPassword());
    }
}

