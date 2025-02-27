package com.example.devicesapi.controller;

import com.example.devicesapi.entity.User;
import com.example.devicesapi.repository.UserRepository;
import com.example.devicesapi.utility.JwtUtil;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.*;
import org.springframework.security.core.Authentication;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    UserRepository userRepository;
    @Autowired
    PasswordEncoder encoder;
    @Autowired
    JwtUtil jwtUtils;

    @Operation(summary = "sign in",
            description = "use your credentials to get JWT token to use in other APIs as Authorization header (Bearer token)")
    @PostMapping("/signin")
    public String authenticateUser(@RequestBody User user) {
            System.out.println(user.getUsername());
            System.out.println(user.getPassword());
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            user.getUsername(),
                            user.getPassword()
                    )
            );
            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            return jwtUtils.generateToken(userDetails.getUsername());


    }

    @Operation(summary = "create a new user",
            description = "pass in user credentials to create a new user")
    @PostMapping("/signup")
    public String registerUser(@RequestBody User user) {
        if (userRepository.existsByUsername(user.getUsername())) {
            return "Error: Username is already taken!";
        }

        // Create new user's account
        User newUser = new User(
                null,
                user.getUsername(),
                encoder.encode(user.getPassword())
        );
        userRepository.save(newUser);
        return "User registered successfully!";
    }

}
