package com.example.gymspringboot.dto.response;

import com.example.gymspringboot.domain.TrainingType;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TrainerListResponseTest {
    @Test
    void testDefaultConstructor() {
        TrainerListResponse trainerListResponse = new TrainerListResponse();
        assertNull(trainerListResponse.getUsername());
        assertNull(trainerListResponse.getFirstName());
        assertNull(trainerListResponse.getLastName());
        assertNull(trainerListResponse.getTrainingType());
    }

    @Test
    void testConstructorWithParameters() {
        TrainingType trainingType = new TrainingType(1L, "trainingType");
        TrainerListResponse trainerListResponse = new TrainerListResponse("username", "firstName", "lastName", trainingType);
        assertEquals("username", trainerListResponse.getUsername());
        assertEquals("firstName", trainerListResponse.getFirstName());
        assertEquals("lastName", trainerListResponse.getLastName());
        assertEquals(trainingType, trainerListResponse.getTrainingType());
    }

    @Test
    void testSetterAndGetter() {
        TrainingType trainingType = new TrainingType(1L, "trainingType");
        TrainerListResponse trainerListResponse = new TrainerListResponse();
        trainerListResponse.setUsername("username");
        trainerListResponse.setFirstName("firstName");
        trainerListResponse.setLastName("lastName");
        trainerListResponse.setTrainingType(trainingType);
        assertEquals("username", trainerListResponse.getUsername());
        assertEquals("firstName", trainerListResponse.getFirstName());
        assertEquals("lastName", trainerListResponse.getLastName());
        assertEquals(trainingType, trainerListResponse.getTrainingType());
    }
}
