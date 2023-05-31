package com.nagarro.exittest_spring.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nagarro.exittest_spring.entity.User;
import com.nagarro.exittest_spring.service.UserService;

@RestController
@RequestMapping("/api/user")
public class UserController {
    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody User login, HttpSession session) {
        User user = userService.login(login);
        
        if(user != null) {
            session.setAttribute("user", user);
            
            // Check the user type
            String userType = user.getUserType();
            if (userType.equals("admin")) {
                return ResponseEntity.ok().body("Admin user logged in successfully");
            } else if (userType.equals("user")) {
                return ResponseEntity.ok().body("Standard user logged in successfully");
            } else {
                return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Unknown user type");
            }
            
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid username or password");
        }
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody User user) {
        String username = userService.registerUser(user);

        if(username != null) {
            return ResponseEntity.ok().body(username);
        } else {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/count")
    public ResponseEntity<Long> getUserCount() {
        Long count = userService.getUserCount();
        return ResponseEntity.ok().body(count);
    }
}