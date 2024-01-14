package com.example.gymspringboot.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TrainingService {

    private final TrainingService trainingService;

    @Autowired
    public TrainingService(TrainingService trainingService) {
        this.trainingService = trainingService;
    }
}
