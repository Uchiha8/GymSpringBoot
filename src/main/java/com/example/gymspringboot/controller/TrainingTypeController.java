package com.example.gymspringboot.controller;

import com.example.gymspringboot.domain.TrainingType;
import com.example.gymspringboot.service.TrainingTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api/v1/trainingtype")
public class TrainingTypeController {
    private final TrainingTypeService trainingTypeService;

    @Autowired
    public TrainingTypeController(TrainingTypeService trainingTypeService) {
        this.trainingTypeService = trainingTypeService;
    }

    @PostMapping(path = "/create")
    public ResponseEntity<TrainingType> create(@RequestBody TrainingType request) {
        return ResponseEntity.ok(trainingTypeService.save(request));
    }

    @GetMapping(path = "/findAll")
    public ResponseEntity<List<TrainingType>> findAll() {
        return ResponseEntity.ok(trainingTypeService.findAll());
    }
}
