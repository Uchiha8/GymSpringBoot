package com.example.gymspringboot.controller;

import com.example.gymspringboot.domain.TrainingType;
import com.example.gymspringboot.service.TrainingTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
    public ResponseEntity<?> create(@RequestBody TrainingType request) {
        if (trainingTypeService.existsTrainingTypeByName(request.getName())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Training type already exists");
        }
        return ResponseEntity.ok(trainingTypeService.save(request));
    }

    @GetMapping(path = "/findAll")
    public ResponseEntity<?> findAll() {
        List<TrainingType> trainingTypes = trainingTypeService.findAll();
        if (trainingTypes.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Training type not found");
        }
        return ResponseEntity.ok(trainingTypeService.findAll());
    }
}
