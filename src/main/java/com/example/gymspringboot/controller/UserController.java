package com.example.gymspringboot.controller;

import com.example.gymspringboot.dto.request.ChangeLoginRequest;
import com.example.gymspringboot.dto.request.LoginRequest;
import com.example.gymspringboot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api/v1/user")
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(path = "/login")
    public ResponseEntity<?> login(LoginRequest request) {
        boolean status = userService.login(request.getUsername(), request.getPassword());
        if (status) {
            return ResponseEntity.ok("Login successful");
        }
        return ResponseEntity.badRequest().body("Login failed");
    }

    @PutMapping(path = "/updatePassword")
    public ResponseEntity<?> updatePassword(ChangeLoginRequest request) {
        boolean status = userService.changePassword(request.getUsername(), request.getOldPassword(), request.getNewPassword());
        if (status) {
            return ResponseEntity.ok("Update password successful");
        }
        return ResponseEntity.badRequest().body("Update password failed");
    }
}
