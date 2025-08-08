package com.usk.ecommerce.serviceimpl;

import com.usk.ecommerce.dto.UserRegistrationRequest;
import com.usk.ecommerce.entity.User;
import com.usk.ecommerce.exception.UserAlreadyExistsException;
import com.usk.ecommerce.repository.UserRepository;
import com.usk.ecommerce.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Base64;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public User registerUser(UserRegistrationRequest registrationRequest) {
        if (userRepository.findByEmail(registrationRequest.getEmail()).isPresent()){
            throw new UserAlreadyExistsException("User with email already exists");
        }
        User user = new User(
                registrationRequest.getFirstName(),
                registrationRequest.getLastName(),
                registrationRequest.getEmail(),
                registrationRequest.getPassword(),
                registrationRequest.getMobileNo()
        );
        return userRepository.save(user);
    }

    @Override
    public User login(String email, String password){
    User user = userRepository.findByEmailAndPassword(email,password).orElse(null);
      if (user != null){
        return user;
     }
      return null;
    }
}
