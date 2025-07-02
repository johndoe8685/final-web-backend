package com.example.demo.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.data.dto.LoginRequest;
import com.example.demo.data.dto.SignupRequest;
import com.example.demo.data.entity.User;
import com.example.demo.data.repository.UserRepository;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    UserRepository userRepository;

    @Autowired
    PasswordEncoder passwordEncoder;
    
    @PostMapping("/signin")
    public ResponseEntity<?> loginUser(@Valid @RequestBody LoginRequest loginRequest) {
        String email = loginRequest.getEmail();

        if(!userRepository.existsByEmail(email)) {
            return ResponseEntity.badRequest().body("This account doesnt exist");
        }

        User user = userRepository.findByEmail(loginRequest.getEmail());
        if(email == null || !passwordEncoder.matches(loginRequest.getPassword(), user.getPassword())) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid email or password");
        }

        Map<String, Object> successResponse = Map.of(
            "message", "Sign in Success!",
            "userId", user.getId(),
            "email", user.getEmail()
        );

        return ResponseEntity.ok(successResponse);
    }

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signupRequest) {
        if(signupRequest.getEmail() == null || signupRequest.getPassword() == null || signupRequest.getCity() == null || signupRequest.getCity() == null) {
            return ResponseEntity.badRequest().body(Map.of("message", "Sign up values are missing"));
        }

        if (userRepository.existsByEmail(signupRequest.getEmail())) {
            return ResponseEntity.badRequest().body(Map.of("message", "This email is already taken"));
        }

        String hashedPassword = passwordEncoder.encode(signupRequest.getPassword());

        User user = new User(signupRequest.getEmail(), hashedPassword, signupRequest.getCountry(), signupRequest.getCity());
        userRepository.save(user);

        return ResponseEntity.status(HttpStatus.CREATED).body(Map.of("message", "Sign in success"));
    }
}
