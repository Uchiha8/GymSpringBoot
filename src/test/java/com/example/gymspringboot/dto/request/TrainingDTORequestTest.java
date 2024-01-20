package com.example.gymspringboot.dto.request;

import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;


public class TrainingDTORequestTest {
    @Test
    void testDefaultConstructor() {
        TrainingDTORequest trainingDTORequest = new TrainingDTORequest();
        assertNull(trainingDTORequest.getTraineeUsername());
        assertNull(trainingDTORequest.getTrainerUsername());
        assertNull(trainingDTORequest.getTrainingName());
        assertNull(trainingDTORequest.getTrainingDate());
        assertNull(trainingDTORequest.getDuration());
    }

    @Test
    void testConstructorWithParameters() {
        Date date = new Date();
        TrainingDTORequest trainingDTORequest = new TrainingDTORequest("traineeUsername", "trainerUsername", "trainingName", date, Duration.ofHours(2));
        assertEquals("traineeUsername", trainingDTORequest.getTraineeUsername());
        assertEquals("trainerUsername", trainingDTORequest.getTrainerUsername());
        assertEquals("trainingName", trainingDTORequest.getTrainingName());
        assertEquals(date, trainingDTORequest.getTrainingDate());
        assertEquals(Duration.ofHours(2), trainingDTORequest.getDuration());
    }

    @Test
    void testSetterAndGetter() {
        Date date = new Date();
        TrainingDTORequest trainingDTORequest = new TrainingDTORequest();
        trainingDTORequest.setTraineeUsername("traineeUsername");
        trainingDTORequest.setTrainerUsername("trainerUsername");
        trainingDTORequest.setTrainingName("trainingName");
        trainingDTORequest.setTrainingDate(date);
        trainingDTORequest.setDuration(Duration.ofHours(2));
        assertEquals("traineeUsername", trainingDTORequest.getTraineeUsername());
        assertEquals("trainerUsername", trainingDTORequest.getTrainerUsername());
        assertEquals("trainingName", trainingDTORequest.getTrainingName());
        assertEquals(date, trainingDTORequest.getTrainingDate());
        assertEquals(Duration.ofHours(2), trainingDTORequest.getDuration());
    }
}
