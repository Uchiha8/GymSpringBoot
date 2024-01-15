package com.example.gymspringboot.controller;

import com.example.gymspringboot.dto.request.TrainerRegistrationRequest;
import com.example.gymspringboot.dto.request.UpdateTrainerRequest;
import com.example.gymspringboot.dto.response.RegistrationResponse;
import com.example.gymspringboot.service.TrainerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/api/v1/trainer")
public class TrainerController {

    private final TrainerService trainerService;

    @Autowired
    public TrainerController(TrainerService trainerService) {
        this.trainerService = trainerService;
    }

    @PostMapping(path = "/register")
    public ResponseEntity<RegistrationResponse> register(@RequestBody TrainerRegistrationRequest request) {
        return ResponseEntity.ok(trainerService.register(request));
    }

    @GetMapping(path = "/profile")
    public ResponseEntity<?> profile(@RequestParam String userName) {
        if (trainerService.existsByUserName(userName)) {
            return ResponseEntity.ok(trainerService.findByUserName(userName));
        }
        return ResponseEntity.badRequest().body("Trainer not found with username: " + userName);
    }

    @PutMapping(path = "/update")
    public ResponseEntity<?> update(@RequestBody UpdateTrainerRequest request) {
        if (trainerService.existsByUserName(request.getUsername())) {
            return ResponseEntity.ok(trainerService.update(request));
        }
        return ResponseEntity.badRequest().body("Trainer not found with username: " + request.getUsername());
    }

    @DeleteMapping(path = "/delete")
    public ResponseEntity<?> delete(@RequestParam String userName) {
        if (trainerService.existsByUserName(userName)) {
            return ResponseEntity.ok(trainerService.delete(userName));
        }
        return ResponseEntity.badRequest().body("Trainer not found with username: " + userName);
    }
}
