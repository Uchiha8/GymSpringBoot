package com.example.gymspringboot.service;

import com.example.gymspringboot.domain.Trainee;
import com.example.gymspringboot.domain.Trainer;
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
        Trainee trainee = traineeRepository.findByUserUsername(request.getTraineeUsername());
        if (trainee == null) {
            throw new RuntimeException("Trainee not found with username: " + request.getTraineeUsername());
        }
        Trainer trainer = trainerRepository.findByUserUsername(request.getTrainerUsername());
        if (trainer == null) {
            throw new RuntimeException("Trainer not found with username: " + request.getTrainerUsername());
        }
        training.setTrainee(trainee);
        training.setTrainer(trainer);
        training.setTrainingName(request.getTrainingName());
        training.setTrainingDate(request.getTrainingDate());
        training.setDuration(request.getDuration());
        training.setTrainingType(trainer.getTrainingType());
        return trainingRepository.save(training);
    }
}
