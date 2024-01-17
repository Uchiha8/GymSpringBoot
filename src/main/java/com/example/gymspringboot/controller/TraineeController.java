package com.example.gymspringboot.controller;

import com.example.gymspringboot.dto.request.ActivateProfileRequest;
import com.example.gymspringboot.dto.request.TraineeRegistrationRequest;
import com.example.gymspringboot.dto.request.TraineeTrainingsListRequest;
import com.example.gymspringboot.dto.request.UpdateTraineeRequest;
import com.example.gymspringboot.dto.response.RegistrationResponse;
import com.example.gymspringboot.service.TraineeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/api/v1/trainee")
public class TraineeController {
    private final TraineeService traineeService;

    @Autowired
    public TraineeController(TraineeService traineeService) {
        this.traineeService = traineeService;
    }

    @PostMapping(path = "/register")
    public ResponseEntity<RegistrationResponse> register(@RequestBody TraineeRegistrationRequest request) {
        return ResponseEntity.ok(traineeService.register(request));
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
        if (traineeService.existsByUserName(request.getUsername())) {
            return ResponseEntity.ok(traineeService.readTraineeTrainingsList(request));
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Trainee not found with username: " + request.getUsername());
    }

    @PutMapping(path = "/update")
    public ResponseEntity<?> update(@RequestBody UpdateTraineeRequest request) {
        if (traineeService.existsByUserName(request.getUsername())) {
            return ResponseEntity.ok(traineeService.updateTrainee(request));
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Trainee not found with username: " + request.getUsername());
    }

    @PatchMapping(path = "/activate")
    public ResponseEntity<?> activate(@RequestBody ActivateProfileRequest request) {
        if (traineeService.existsByUserName(request.getUsername())) {
            return ResponseEntity.ok(traineeService.activateDeactivateTrainee(request));
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Trainee not found with username: " + request.getUsername());
    }

    @DeleteMapping(path = "/delete")
    public ResponseEntity<?> delete(@RequestParam String userName) {
        if (traineeService.existsByUserName(userName)) {
            return ResponseEntity.ok(traineeService.deleteTrainee(userName));
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Trainee not found with username: " + userName);

    }

}
