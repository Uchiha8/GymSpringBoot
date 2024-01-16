package com.example.gymspringboot.service;

import com.example.gymspringboot.domain.*;
import com.example.gymspringboot.dto.request.*;
import com.example.gymspringboot.dto.response.*;
import com.example.gymspringboot.repository.TrainerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TrainerService {

    private final TrainerRepository trainerRepository;
    private final UserService userService;
    private final TrainingTypeService trainingTypeService;

    @Autowired
    public TrainerService(TrainerRepository trainerRepository, UserService userService, TrainingTypeService trainingTypeService) {
        this.trainerRepository = trainerRepository;
        this.userService = userService;
        this.trainingTypeService = trainingTypeService;
    }

    public RegistrationResponse register(TrainerRegistrationRequest request) {
        //from dto to entity
        User user = userService.save(new User(null, request.getFirstName(), request.getLastName(), null, null, false));
        TrainingType trainingType = trainingTypeService.findByTrainingTypeName(request.getTrainingType());
        Trainer trainer = trainerRepository.save(new Trainer(null, trainingType, user, List.of()));
        //from entity to dto
        return new RegistrationResponse(trainer.getUser().getUsername(), user.getPassword());
    }

    public TrainerProfileResponse findByUserName(String userName) {
        Trainer trainer = trainerRepository.findByUserUsername(userName);
        List<Trainee> trainees = trainer.getTrainings().stream().map(Training::getTrainee).toList();
        List<TraineeListResponse> traineeListResponses = new ArrayList<>();
        if (!trainees.isEmpty()) {
            traineeListResponses = trainees.stream().map(trainee -> new TraineeListResponse(trainee.getUser().getUsername(), trainee.getUser().getFirstName(), trainee.getUser().getLastName())).toList();
        }
        return new TrainerProfileResponse(trainer.getUser().getFirstName(), trainer.getUser().getLastName(), trainer.getTrainingType(), trainer.getUser().getActive(), traineeListResponses);
    }


    public boolean existsByUserName(String userName) {
        return trainerRepository.existsByUserUsername(userName);
    }

    public UpdateTrainerResponse update(UpdateTrainerRequest request) {
        Trainer trainer = trainerRepository.findByUserUsername(request.getUsername());
        List<Trainee> trainees = trainer.getTrainings().stream().map(Training::getTrainee).toList();
        List<TraineeListResponse> traineeListResponses = new ArrayList<>();
        if (!trainees.isEmpty()) {
            traineeListResponses = trainees.stream().map(trainee -> new TraineeListResponse(trainee.getUser().getUsername(), trainee.getUser().getFirstName(), trainee.getUser().getLastName())).toList();
        }
        TrainingType trainingType = trainingTypeService.findByTrainingTypeName(request.getTrainingType().getName());
        trainer.getUser().setFirstName(request.getFirstName());
        trainer.getUser().setLastName(request.getLastName());
        trainer.getUser().setActive(request.getActive());
        trainer.setTrainingType(trainingType);
        trainerRepository.save(trainer);
        return new UpdateTrainerResponse(trainer.getUser().getUsername(), trainer.getUser().getFirstName(), trainer.getUser().getLastName(), trainer.getTrainingType(), trainer.getUser().getActive(), traineeListResponses);
    }

    public boolean delete(String userName) {
        if (existsByUserName(userName)) {
            return trainerRepository.deleteByUserUsername(userName);
        }
        return false;
    }

    public List<ActiveTrainerResponse> findAllActiveTrainersNotAssignedTrainee(String username) {
        List<Trainer> trainers = trainerRepository.findAll();
        List<ActiveTrainerResponse> activeTrainerResponses = new ArrayList<>();
        for (Trainer trainer : trainers) {
            if (trainer.getUser().getActive()) {
                if (trainer.getTrainings().stream().noneMatch(training -> training.getTrainee().getUser().getUsername().equals(username))) {
                    activeTrainerResponses.add(new ActiveTrainerResponse(trainer.getUser().getUsername(), trainer.getUser().getFirstName(), trainer.getUser().getLastName(), trainer.getTrainingType()));
                }
            }
        }
        return activeTrainerResponses;
    }

    public List<TrainerTrainingsListResponse> readTrainerTrainingsList(TrainerTrainingListRequest request) {
        Trainer trainer = trainerRepository.findByUserUsername(request.getUsername());
        List<Training> trainings = new ArrayList<>(trainer.getTrainings());
        if (request.getTraineeUsername() != null) {
            trainings.removeIf(training ->
                    !training.getTrainee().getUser().getUsername().equals(request.getTraineeUsername()));
        }
        return trainings.stream()
                .map(training -> new TrainerTrainingsListResponse(
                        training.getTrainingName(),
                        training.getTrainingDate(),
                        training.getTrainingType(),
                        training.getDuration(),
                        training.getTrainee().getUser().getUsername()
                )).toList();
    }

    public boolean activateDeactivateTrainee(ActivateProfileRequest request) {
        if (existsByUserName(request.getUsername())) {
            return trainerRepository.updateActive(request.getUsername(), request.getActive());
        }
        return false;
    }
}
