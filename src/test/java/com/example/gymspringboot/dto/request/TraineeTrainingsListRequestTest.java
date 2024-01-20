package com.example.gymspringboot.dto.request;

import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;


public class TraineeTrainingsListRequestTest {
    @Test
    void testDefaultConstructor() {
        TraineeTrainingsListRequest traineeTrainingsListRequest = new TraineeTrainingsListRequest();
        assertNull(traineeTrainingsListRequest.getUsername());
        assertNull(traineeTrainingsListRequest.getTrainerUsername());
        assertNull(traineeTrainingsListRequest.getTrainingType());
        assertNull(traineeTrainingsListRequest.getPeriodFrom());
        assertNull(traineeTrainingsListRequest.getPeriodTo());
    }

    @Test
    void testConstructorWithParameters() {
        Date periodFrom = new Date();
        Date periodTo = new Date();
        TraineeTrainingsListRequest traineeTrainingsListRequest = new TraineeTrainingsListRequest("username", periodFrom, periodTo, "trainerUsername", "trainingType");
        assertEquals("username", traineeTrainingsListRequest.getUsername());
        assertEquals("trainerUsername", traineeTrainingsListRequest.getTrainerUsername());
        assertEquals("trainingType", traineeTrainingsListRequest.getTrainingType());
        assertEquals(periodFrom, traineeTrainingsListRequest.getPeriodFrom());
        assertEquals(periodTo, traineeTrainingsListRequest.getPeriodTo());
    }

    @Test
    void testSetterAndGetter() {
        Date periodFrom = new Date();
        Date periodTo = new Date();
        TraineeTrainingsListRequest traineeTrainingsListRequest = new TraineeTrainingsListRequest();
        traineeTrainingsListRequest.setUsername("username");
        traineeTrainingsListRequest.setTrainerUsername("trainerUsername");
        traineeTrainingsListRequest.setTrainingType("trainingType");
        traineeTrainingsListRequest.setPeriodFrom(periodFrom);
        traineeTrainingsListRequest.setPeriodTo(periodTo);
        assertEquals("username", traineeTrainingsListRequest.getUsername());
        assertEquals("trainerUsername", traineeTrainingsListRequest.getTrainerUsername());
        assertEquals("trainingType", traineeTrainingsListRequest.getTrainingType());
        assertEquals(periodFrom, traineeTrainingsListRequest.getPeriodFrom());
        assertEquals(periodTo, traineeTrainingsListRequest.getPeriodTo());
    }

}
