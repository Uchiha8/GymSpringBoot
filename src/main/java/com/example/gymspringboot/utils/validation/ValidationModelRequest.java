package com.example.gymspringboot.utils.validation;

import com.example.gymspringboot.dto.request.*;
import org.springframework.stereotype.Component;

@Component
public class ValidationModelRequest {
    public boolean activateProfileValid(ActivateProfileRequest request) {
        if (request.getUsername() == null || request.getActive() == null) {
            return false;
        }
        return true;
    }

    public boolean changeLoginValid(ChangeLoginRequest request) {
        if (request.getNewPassword() == null || request.getOldPassword() == null || request.getUsername() == null) {
            return false;
        }
        return true;
    }

    public boolean loginValid(LoginRequest request) {
        if (request.getUsername() == null || request.getPassword() == null) {
            return false;
        }
        return true;
    }

    public boolean traineeRegisterValid(TraineeRegistrationRequest request) {
        if (request.getFirstName() == null || request.getLastName() == null) {
            return false;
        }
        return true;
    }

    public boolean traineeTrainingsValid(TraineeTrainingsListRequest request) {
        if (request.getUsername() == null) {
            return false;
        }
        return true;
    }

    public boolean trainerRegisterValid(TrainerRegistrationRequest request) {
        if (request.getFirstName() == null || request.getLastName() == null || request.getTrainingType() == null) {
            return false;
        }
        return true;
    }


    public boolean trainerTrainingsValid(TrainerTrainingListRequest request) {
        if (request.getUsername() == null) {
            return false;
        }
        return true;
    }

    public boolean trainingDtoValid(TrainingDTORequest request) {
        if (request.getTrainerUsername() == null ||
                request.getTraineeUsername() == null ||
                request.getTrainingDate() == null ||
                request.getTrainingName() == null ||
                request.getDuration() == null) {
            return false;
        }
        return true;
    }

    public boolean updateTraineeValid(UpdateTraineeRequest request) {
        if (request.getUsername() == null || request.getFirstName() == null || request.getLastName() == null || request.getActive() == null) {
            return false;
        }
        return true;
    }

    public boolean updateTrainerValid(UpdateTrainerRequest request) {
        if (request.getUsername() == null || request.getFirstName() == null || request.getLastName() == null || request.getTrainingType() == null || request.getActive() == null) {
            return false;
        }
        return true;
    }
}

