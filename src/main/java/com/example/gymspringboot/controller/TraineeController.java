package com.example.gymspringboot.controller;

import com.example.gymspringboot.dto.request.ActivateProfileRequest;
import com.example.gymspringboot.dto.request.TraineeRegistrationRequest;
import com.example.gymspringboot.dto.request.TraineeTrainingsListRequest;
import com.example.gymspringboot.dto.request.UpdateTraineeRequest;
import com.example.gymspringboot.service.TraineeService;
import com.example.gymspringboot.utils.validation.ValidationModelRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/api/v1/trainee")
public class TraineeController {
    private final TraineeService traineeService;
    private final ValidationModelRequest validation;

    @Autowired
    public TraineeController(TraineeService traineeService, ValidationModelRequest validation) {
        this.traineeService = traineeService;
        this.validation = validation;
    }

    @PostMapping(path = "/register")
    public ResponseEntity<?> register(@RequestBody TraineeRegistrationRequest request) {
        if (!validation.traineeRegisterValid(request)) {
            return ResponseEntity.badRequest().body("Invalid request");
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(traineeService.register(request));
    }

    @GetMapping(path = "/profile")
    public ResponseEntity<?> profile(@RequestParam String userName) {
        if (traineeService.existsByUserName(userName)) {
            return ResponseEntity.ok(traineeService.findByUserName(userName));
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Trainee not found with username: " + userName);

    }

    @GetMapping(path = "/traineeTrainings")
    public ResponseEntity<?> traineeTrainings(@RequestBody TraineeTrainingsListRequest request) {
        if (!validation.traineeTrainingsValid(request)) {
            return ResponseEntity.badRequest().body("Invalid request");
        } else if (!traineeService.existsByUserName(request.getUsername())) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Trainee not found with username: " + request.getUsername());
        }
        return ResponseEntity.ok(traineeService.readTraineeTrainingsList(request));
    }

    @PutMapping(path = "/update")
    public ResponseEntity<?> update(@RequestBody UpdateTraineeRequest request) {
        if (!validation.updateTraineeValid(request)) {
            return ResponseEntity.badRequest().body("Invalid request");
        } else if (!traineeService.existsByUserName(request.getUsername())) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Trainee not found with username: " + request.getUsername());
        }
        return ResponseEntity.ok(traineeService.updateTrainee(request));
    }

    @PatchMapping(path = "/activate")
    public ResponseEntity<?> activate(@RequestBody ActivateProfileRequest request) {
        if (!validation.activateProfileValid(request)) {
            return ResponseEntity.badRequest().body("Invalid request");
        } else if (!traineeService.existsByUserName(request.getUsername())) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Trainee not found with username: " + request.getUsername());
        }
        try {
            traineeService.activateDeactivateTrainee(request);
            return ResponseEntity.ok("Trainee " + (request.getActive() ? "activated" : "deactivated"));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping(path = "/delete")
    public ResponseEntity<?> delete(@RequestParam String userName) {
        if (!traineeService.existsByUserName(userName)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Trainee not found with username: " + userName);
        }
        traineeService.deleteTrainee(userName);
        return ResponseEntity.ok("Trainee deleted");
    }

}
