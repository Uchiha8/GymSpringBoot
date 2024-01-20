package com.example.gymspringboot.dto.request;

import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;


public class TrainerTrainingListRequestTest {
    @Test
    void testDefaultConstructor() {
        TrainerTrainingListRequest trainerTrainingListRequest = new TrainerTrainingListRequest();
        assertNull(trainerTrainingListRequest.getUsername());
        assertNull(trainerTrainingListRequest.getPeriodFrom());
        assertNull(trainerTrainingListRequest.getPeriodTo());
        assertNull(trainerTrainingListRequest.getTraineeUsername());
    }

    @Test
    void testConstructorWithParameters() {
        Date periodFrom = new Date();
        Date periodTo = new Date();
        TrainerTrainingListRequest trainerTrainingListRequest = new TrainerTrainingListRequest("username", periodFrom, periodTo, "traineeUsername");
        assertEquals("username", trainerTrainingListRequest.getUsername());
        assertEquals(periodFrom, trainerTrainingListRequest.getPeriodFrom());
        assertEquals(periodTo, trainerTrainingListRequest.getPeriodTo());
        assertEquals("traineeUsername", trainerTrainingListRequest.getTraineeUsername());
    }

    @Test
    void testSetterAndGetter() {
        Date periodFrom = new Date();
        Date periodTo = new Date();
        TrainerTrainingListRequest trainerTrainingListRequest = new TrainerTrainingListRequest();
        trainerTrainingListRequest.setUsername("username");
        trainerTrainingListRequest.setPeriodFrom(periodFrom);
        trainerTrainingListRequest.setPeriodTo(periodTo);
        trainerTrainingListRequest.setTraineeUsername("traineeUsername");
        assertEquals("username", trainerTrainingListRequest.getUsername());
        assertEquals(periodFrom, trainerTrainingListRequest.getPeriodFrom());
        assertEquals(periodTo, trainerTrainingListRequest.getPeriodTo());
        assertEquals("traineeUsername", trainerTrainingListRequest.getTraineeUsername());
    }
}
