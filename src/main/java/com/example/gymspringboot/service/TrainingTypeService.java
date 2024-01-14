package com.example.gymspringboot.service;

import com.example.gymspringboot.repository.TrainingTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TrainingTypeService {
    private final TrainingTypeRepository trainingTypeService;

    @Autowired
    public TrainingTypeService(TrainingTypeRepository trainingTypeService) {
        this.trainingTypeService = trainingTypeService;
    }

}
