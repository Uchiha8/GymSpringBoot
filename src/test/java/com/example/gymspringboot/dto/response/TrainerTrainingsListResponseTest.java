package com.example.gymspringboot.dto.response;

import com.example.gymspringboot.domain.TrainingType;
import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

public class TrainerTrainingsListResponseTest {
    @Test
    void testDefaultConstructor() {
        TrainerTrainingsListResponse trainerTrainingsListResponse = new TrainerTrainingsListResponse();
        assertNull(trainerTrainingsListResponse.getTrainingType());
        assertNull(trainerTrainingsListResponse.getTrainingDate());
        assertNull(trainerTrainingsListResponse.getTrainingName());
        assertNull(trainerTrainingsListResponse.getDuration());
        assertNull(trainerTrainingsListResponse.getTraineeUsername());
    }

    @Test
    void testConstructorWithParameters() {
        TrainingType trainingType = new TrainingType(1L, "trainingType");
        Date date = new Date();
        TrainerTrainingsListResponse trainerTrainingsListResponse = new TrainerTrainingsListResponse("trainingName", date, trainingType, Duration.ofHours(2), "traineeUsername");
        assertEquals(trainingType, trainerTrainingsListResponse.getTrainingType());
        assertEquals("traineeUsername", trainerTrainingsListResponse.getTraineeUsername());
        assertEquals("trainingName", trainerTrainingsListResponse.getTrainingName());
        assertEquals(date, trainerTrainingsListResponse.getTrainingDate());
        assertEquals(Duration.ofHours(2), trainerTrainingsListResponse.getDuration());
    }

    @Test
    void testSetterAndGetter() {
        TrainingType trainingType = new TrainingType(1L, "trainingType");
        Date date = new Date();
        TrainerTrainingsListResponse trainerTrainingsListResponse = new TrainerTrainingsListResponse();
        trainerTrainingsListResponse.setTrainingType(trainingType);
        trainerTrainingsListResponse.setTraineeUsername("traineeUsername");
        trainerTrainingsListResponse.setTrainingName("trainingName");
        trainerTrainingsListResponse.setTrainingDate(date);
        trainerTrainingsListResponse.setDuration(Duration.ofHours(2));
        assertEquals(trainingType, trainerTrainingsListResponse.getTrainingType());
        assertEquals("traineeUsername", trainerTrainingsListResponse.getTraineeUsername());
        assertEquals("trainingName", trainerTrainingsListResponse.getTrainingName());
        assertEquals(date, trainerTrainingsListResponse.getTrainingDate());
        assertEquals(Duration.ofHours(2), trainerTrainingsListResponse.getDuration());
    }
}
