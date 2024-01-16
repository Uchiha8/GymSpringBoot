package com.example.gymspringboot.controller;

import com.example.gymspringboot.dto.request.ActivateProfileRequest;
import com.example.gymspringboot.dto.request.TrainerRegistrationRequest;
import com.example.gymspringboot.dto.request.UpdateTrainerRequest;
import com.example.gymspringboot.dto.response.RegistrationResponse;
import com.example.gymspringboot.service.TraineeService;
import com.example.gymspringboot.service.TrainerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/api/v1/trainer")
public class TrainerController {

    private final TrainerService trainerService;
    private final TraineeService traineeService;

    @Autowired
    public TrainerController(TrainerService trainerService, TraineeService traineeService) {
        this.trainerService = trainerService;
        this.traineeService = traineeService;
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

    @GetMapping(path = "/activeTrainer")
    public ResponseEntity<?> activeTrainer(@RequestParam String username) {
        if (traineeService.existsByUserName(username)) {
            return ResponseEntity.ok(trainerService.findAllActiveTrainersNotAssignedTrainee(username));
        }
        return ResponseEntity.badRequest().body("Trainee not found with username: " + username);
    }

    @PutMapping(path = "/update")
    public ResponseEntity<?> update(@RequestBody UpdateTrainerRequest request) {
        if (trainerService.existsByUserName(request.getUsername())) {
            return ResponseEntity.ok(trainerService.update(request));
        }
        return ResponseEntity.badRequest().body("Trainer not found with username: " + request.getUsername());
    }

    @PatchMapping(path = "/activate")
    public ResponseEntity<?> activate(@RequestBody ActivateProfileRequest request) {
        if (trainerService.existsByUserName(request.getUsername())) {
            return ResponseEntity.ok(trainerService.activateDeactivateTrainee(request));
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Trainer not found with username: " + request.getUsername());
    }

    @DeleteMapping(path = "/delete")
    public ResponseEntity<?> delete(@RequestParam String userName) {
        if (trainerService.existsByUserName(userName)) {
            return ResponseEntity.ok(trainerService.delete(userName));
        }
        return ResponseEntity.badRequest().body("Trainer not found with username: " + userName);
    }
}
