package com.example.gymspringboot.dto.response;

import com.example.gymspringboot.domain.TrainingType;
import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

public class TraineeTrainingsListResponseTest {
    @Test
    void testDefaultConstructor() {
        TraineeTrainingsListResponse traineeTrainingsListResponse = new TraineeTrainingsListResponse();
        assertNull(traineeTrainingsListResponse.getTrainingType());
        assertNull(traineeTrainingsListResponse.getTrainingDate());
        assertNull(traineeTrainingsListResponse.getTrainingName());
        assertNull(traineeTrainingsListResponse.getDuration());
        assertNull(traineeTrainingsListResponse.getTrainerUsername());
    }

    @Test
    void testConstructorWithParameters() {
        Date date = new Date();
        TrainingType trainingType = new TrainingType(1L, "trainingType");
        TraineeTrainingsListResponse traineeTrainingsListResponse = new TraineeTrainingsListResponse("trainingName", date, trainingType, Duration.ofHours(2), "trainerUsername");
        assertEquals(trainingType, traineeTrainingsListResponse.getTrainingType());
        assertEquals(date, traineeTrainingsListResponse.getTrainingDate());
        assertEquals("trainingName", traineeTrainingsListResponse.getTrainingName());
        assertEquals(Duration.ofHours(2), traineeTrainingsListResponse.getDuration());
        assertEquals("trainerUsername", traineeTrainingsListResponse.getTrainerUsername());
    }

    @Test
    void testSetterAndGetter() {
        Date date = new Date();
        TrainingType trainingType = new TrainingType(1L, "trainingType");
        TraineeTrainingsListResponse traineeTrainingsListResponse = new TraineeTrainingsListResponse();
        traineeTrainingsListResponse.setTrainingType(trainingType);
        traineeTrainingsListResponse.setTrainingDate(date);
        traineeTrainingsListResponse.setTrainingName("trainingName");
        traineeTrainingsListResponse.setDuration(Duration.ofHours(2));
        traineeTrainingsListResponse.setTrainerUsername("trainerUsername");
        assertEquals(trainingType, traineeTrainingsListResponse.getTrainingType());
        assertEquals(date, traineeTrainingsListResponse.getTrainingDate());
        assertEquals("trainingName", traineeTrainingsListResponse.getTrainingName());
        assertEquals(Duration.ofHours(2), traineeTrainingsListResponse.getDuration());
        assertEquals("trainerUsername", traineeTrainingsListResponse.getTrainerUsername());
    }
}
