package com.example.gymspringboot.controller;

import com.example.gymspringboot.dto.request.ActivateProfileRequest;
import com.example.gymspringboot.dto.request.TrainerRegistrationRequest;
import com.example.gymspringboot.dto.request.UpdateTrainerRequest;
import com.example.gymspringboot.service.TraineeService;
import com.example.gymspringboot.service.TrainerService;
import com.example.gymspringboot.utils.validation.ValidationModelRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/api/v1/trainer")
public class TrainerController {

    private final TrainerService trainerService;
    private final TraineeService traineeService;
    private final ValidationModelRequest validation;

    @Autowired
    public TrainerController(TrainerService trainerService, TraineeService traineeService, ValidationModelRequest validation) {
        this.trainerService = trainerService;
        this.traineeService = traineeService;
        this.validation = validation;
    }

    @PostMapping(path = "/register")
    public ResponseEntity<?> register(@RequestBody TrainerRegistrationRequest request) {
        if (!validation.trainerRegisterValid(request)) {
            return ResponseEntity.badRequest().body("Invalid request");
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(trainerService.register(request));
    }

    @GetMapping(path = "/profile")
    public ResponseEntity<?> profile(@RequestParam String userName) {
        if (trainerService.existsByUserName(userName)) {
            return ResponseEntity.ok(trainerService.findByUserName(userName));
        }
        return ResponseEntity.badRequest().body("Trainer not found with username: " + userName);
    }

    @GetMapping(path = "/activeTrainer")
    public ResponseEntity<?> activeTrainer(@RequestParam String username) {
        if (traineeService.existsByUserName(username)) {
            return ResponseEntity.ok(trainerService.findAllActiveTrainersNotAssignedTrainee(username));
        }
        return ResponseEntity.badRequest().body("Trainee not found with username: " + username);
    }

    @PutMapping(path = "/update")
    public ResponseEntity<?> update(@RequestBody UpdateTrainerRequest request) {
        if (!validation.updateTrainerValid(request)) {
            return ResponseEntity.badRequest().body("Invalid request");
        } else if (!trainerService.existsByUserName(request.getUsername())) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Trainer not found with username: " + request.getUsername());
        }
        return ResponseEntity.ok(trainerService.update(request));
    }

    @PatchMapping(path = "/activate")
    public ResponseEntity<?> activate(@RequestBody ActivateProfileRequest request) {
        if (!validation.activateProfileValid(request)) {
            return ResponseEntity.badRequest().body("Invalid request");
        } else if (!trainerService.existsByUserName(request.getUsername())) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Trainer not found with username: " + request.getUsername());
        }
        return ResponseEntity.ok(trainerService.activateDeactivateTrainer(request));
    }

    @DeleteMapping(path = "/delete")
    public ResponseEntity<?> delete(@RequestParam String userName) {
        if (trainerService.existsByUserName(userName)) {
            return ResponseEntity.ok(trainerService.delete(userName));
        }
        return ResponseEntity.badRequest().body("Trainer not found with username: " + userName);
    }
}
