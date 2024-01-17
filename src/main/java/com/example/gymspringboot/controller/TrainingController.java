package com.example.gymspringboot.controller;

import com.example.gymspringboot.dto.request.TrainingDTORequest;
import com.example.gymspringboot.service.TrainingService;
import com.example.gymspringboot.utils.validation.ValidationModelRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api/v1/training")
public class TrainingController {

    private final TrainingService trainingService;
    private final ValidationModelRequest validation;

    @Autowired
    public TrainingController(TrainingService trainingService, ValidationModelRequest validation) {
        this.trainingService = trainingService;
        this.validation = validation;
    }


    @PostMapping(path = "/create")
    public ResponseEntity<?> create(@RequestBody TrainingDTORequest request) {
        if (!validation.trainingDtoValid(request)) {
            return ResponseEntity.badRequest().body("Invalid request");
        }
        if (trainingService.save(request) != null) {
            return ResponseEntity.status(HttpStatus.CREATED).body("Training created");
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Something went from");
    }
}
