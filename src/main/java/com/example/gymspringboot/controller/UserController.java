package com.example.gymspringboot.controller;

import com.example.gymspringboot.dto.request.ChangeLoginRequest;
import com.example.gymspringboot.dto.request.LoginRequest;
import com.example.gymspringboot.service.UserService;
import com.example.gymspringboot.utils.validation.ValidationModelRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/api/v1/user")
public class UserController {
    private final UserService userService;
    private final ValidationModelRequest validation;

    @Autowired
    public UserController(UserService userService, ValidationModelRequest validation) {
        this.userService = userService;
        this.validation = validation;
    }

    @GetMapping(path = "/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest request) {
        if (!validation.loginValid(request)) {
            return ResponseEntity.badRequest().body("Invalid request");
        } else if (!userService.existsByUsername(request.getUsername())) {
            return ResponseEntity.badRequest().body("User not found with username: " + request.getUsername());
        } else if (userService.login(request.getUsername(), request.getPassword())) {
            return ResponseEntity.ok("Login successful");
        } else {
            return ResponseEntity.badRequest().body("Login failed");
        }
    }

    @PutMapping(path = "/updatePassword")
    public ResponseEntity<?> updatePassword(ChangeLoginRequest request) {
        if (!validation.changeLoginValid(request)) {
            return ResponseEntity.badRequest().body("Invalid request");
        } else if (!userService.existsByUsername(request.getUsername())) {
            return ResponseEntity.badRequest().body("User not found with username: " + request.getUsername());
        }
        boolean status = userService.changePassword(request.getUsername(), request.getOldPassword(), request.getNewPassword());
        if (status) {
            return ResponseEntity.ok("Update password successful");
        }
        return ResponseEntity.badRequest().body("Update password failed");
    }
}
