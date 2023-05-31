package com.nagarro.exittest_spring.service;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.nagarro.exittest_spring.entity.User;
import com.nagarro.exittest_spring.repo.UserRepository;

@Service
public class UserService {
    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public User login(User login) {
        User user = userRepository.findByUsername(login.getUsername());
        
        if(user != null && passwordEncoder.matches(login.getPassword(), user.getPassword())) {
            return user;
        } else {
            return null;
        }
    }

    public String registerUser(User user) {
        // Check if username already exists
        if(userRepository.findByUsername(user.getUsername()) != null) {
            return null;
        }

        // Encode password
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        // Save the user
        userRepository.save(user);

        return user.getUsername();
    }

    public long getUserCount() {
        return userRepository.count();
    }
}

