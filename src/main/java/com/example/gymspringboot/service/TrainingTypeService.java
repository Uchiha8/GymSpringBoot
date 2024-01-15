package com.example.gymspringboot.service;

import com.example.gymspringboot.domain.TrainingType;
import com.example.gymspringboot.repository.TrainingTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TrainingTypeService {
    private final TrainingTypeRepository trainingTypeRepository;

    @Autowired
    public TrainingTypeService(TrainingTypeRepository trainingTypeRepository) {
        this.trainingTypeRepository = trainingTypeRepository;
    }

    public TrainingType save(TrainingType trainingType) {
        return trainingTypeRepository.save(trainingType);
    }

    public TrainingType findByTrainingTypeName(String trainingTypeName) {
        return trainingTypeRepository.findByName(trainingTypeName);
    }

    public List<TrainingType> findAll() {
        return trainingTypeRepository.findAll();
    }

}
