package com.example.gymspringboot.service;

import com.example.gymspringboot.domain.Trainee;
import com.example.gymspringboot.domain.Trainer;
import com.example.gymspringboot.domain.Training;
import com.example.gymspringboot.domain.User;
import com.example.gymspringboot.dto.request.ActivateProfileRequest;
import com.example.gymspringboot.dto.request.TraineeRegistrationRequest;
import com.example.gymspringboot.dto.request.TraineeTrainingsListRequest;
import com.example.gymspringboot.dto.request.UpdateTraineeRequest;
import com.example.gymspringboot.dto.response.*;
import com.example.gymspringboot.repository.TraineeRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class TraineeService {
    private final TraineeRepository traineeRepository;
    private final UserService userService;

    @Autowired
    public TraineeService(TraineeRepository traineeRepository, UserService userService) {
        this.traineeRepository = traineeRepository;
        this.userService = userService;
    }

    public RegistrationResponse register(TraineeRegistrationRequest request) {
        //from dto to entity
        User user = userService.save(new User(null, request.getFirstName(), request.getLastName(), null, null, false));
        Trainee trainee = traineeRepository.save(new Trainee(null, request.getDateOfBirth(), request.getAddress(), user, List.of()));
        //from entity to dto
        return new RegistrationResponse(trainee.getUser().getUsername(), user.getPassword());
    }

    public TraineeProfileResponse findByUserName(String userName) {
        Trainee trainee = traineeRepository.findByUserUsername(userName);
        List<Trainer> trainers = trainee.getTrainings().stream().map(Training::getTrainer).toList();
        List<TrainerListResponse> trainerListResponses = new ArrayList<>();
        if (!trainers.isEmpty()) {
            trainerListResponses = trainers.stream().map(trainer -> new TrainerListResponse(trainer.getUser().getUsername(), trainer.getUser().getFirstName(), trainer.getUser().getLastName(), trainer.getTrainingType())).toList();
        }
        return new TraineeProfileResponse(trainee.getUser().getFirstName(), trainee.getUser().getLastName(), trainee.getDateOfBirth(), trainee.getAddress(), trainee.getUser().getActive(), trainerListResponses);
    }

    public boolean existsByUserName(String userName) {
        return traineeRepository.existsByUserUsername(userName);
    }

    public UpdateTraineeResponse updateTrainee(UpdateTraineeRequest request) {
        Trainee trainee = traineeRepository.findByUserUsername(request.getUsername());
        List<Trainer> trainers = trainee.getTrainings().stream().map(Training::getTrainer).toList();
        List<TrainerListResponse> trainerListResponses = new ArrayList<>();
        if (!trainers.isEmpty()) {
            trainerListResponses = trainers.stream().map(trainer -> new TrainerListResponse(trainer.getUser().getUsername(), trainer.getUser().getFirstName(), trainer.getUser().getLastName(), trainer.getTrainingType())).toList();
        }
        trainee.getUser().setFirstName(request.getFirstName());
        trainee.getUser().setLastName(request.getLastName());
        trainee.getUser().setActive(request.getActive());
        trainee.setDateOfBirth(request.getDateOfBirth());
        trainee.setAddress(request.getAddress());
        trainee.getUser().setUsername(userService.usernameGenerator(request.getFirstName(), request.getLastName()));
        traineeRepository.save(trainee);
        return new UpdateTraineeResponse(trainee.getUser().getUsername(), trainee.getUser().getFirstName(), trainee.getUser().getLastName(), trainee.getDateOfBirth(), trainee.getAddress(), trainee.getUser().getActive(), trainerListResponses);
    }

    public void deleteTrainee(String username) {
        Trainee trainee = traineeRepository.findByUserUsername(username);
        traineeRepository.deleteById(trainee.getId());
    }

    public List<TraineeTrainingsListResponse> readTraineeTrainingsList(TraineeTrainingsListRequest request) {
        Trainee trainee = traineeRepository.findByUserUsername(request.getUsername());
        List<Training> trainings = new ArrayList<>(trainee.getTrainings());
        if (request.getTrainerUsername() != null) {
            trainings.removeIf(training ->
                    !training.getTrainer().getUser().getUsername().equals(request.getTrainerUsername()));
        }
        return trainings.stream()
                .map(training -> new TraineeTrainingsListResponse(
                        training.getTrainingName(),
                        training.getTrainingDate(),
                        training.getTrainer().getTrainingType(),
                        training.getDuration(),
                        training.getTrainer().getUser().getUsername())).toList();
    }

    public void activateDeactivateTrainee(ActivateProfileRequest request) {
        Trainee trainee = traineeRepository.findByUserUsername(request.getUsername());
        if (trainee.getUser().getActive() == request.getActive()) {
            throw new RuntimeException("Trainee already " + (request.getActive() ? "activated" : "deactivated"));
        }
        trainee.getUser().setActive(request.getActive());
        traineeRepository.save(trainee);
    }
}
