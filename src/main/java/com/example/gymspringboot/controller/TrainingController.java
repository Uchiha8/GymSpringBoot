package com.example.gymspringboot.controller;

import com.example.gymspringboot.dto.request.TrainingDTORequest;
import com.example.gymspringboot.service.TrainingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api/v1/training")
public class TrainingController {

    private final TrainingService trainingService;

    @Autowired
    public TrainingController(TrainingService trainingService) {
        this.trainingService = trainingService;
    }


    @PostMapping(path = "/create")
    public ResponseEntity<?> create(@RequestBody TrainingDTORequest request) {
        return ResponseEntity.ok(trainingService.save(request));
    }
}
