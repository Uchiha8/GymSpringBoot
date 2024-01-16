package com.example.gymspringboot.service;

import com.example.gymspringboot.domain.Training;
import com.example.gymspringboot.dto.request.TrainingDTORequest;
import com.example.gymspringboot.repository.TraineeRepository;
import com.example.gymspringboot.repository.TrainerRepository;
import com.example.gymspringboot.repository.TrainingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TrainingService {
    private final TrainingRepository trainingRepository;
    private final TrainerRepository trainerRepository;
    private final TraineeRepository traineeRepository;

    @Autowired
    public TrainingService(TrainingRepository trainingRepository, TrainerRepository trainerRepository, TraineeRepository traineeRepository) {
        this.trainingRepository = trainingRepository;
        this.trainerRepository = trainerRepository;
        this.traineeRepository = traineeRepository;
    }

    public Training save(TrainingDTORequest request) {
        //from entity to model
        Training training = new Training();
        training.setId(null);
        training.setTrainee(traineeRepository.findByUserUsername(request.getTraineeUsername()));
        training.setTrainer(trainerRepository.findByUserUsername(request.getTrainerUsername()));
        training.setTrainingName(request.getTrainingName());
        training.setTrainingDate(request.getTrainingDate());
        training.setDuration(request.getDuration());
        training.setTrainingType(trainerRepository.findByUserUsername(request.getTrainerUsername()).getTrainingType());
        return trainingRepository.save(training);
    }
}
