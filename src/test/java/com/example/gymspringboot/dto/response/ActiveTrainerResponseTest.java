package com.example.gymspringboot.dto.response;

import com.example.gymspringboot.domain.TrainingType;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ActiveTrainerResponseTest {
    @Test
    void testDefaultConstructor() {
        ActiveTrainerResponse activeTrainerResponse = new ActiveTrainerResponse();
        assertNull(activeTrainerResponse.getUsername());
        assertNull(activeTrainerResponse.getFirstName());
        assertNull(activeTrainerResponse.getLastName());
        assertNull(activeTrainerResponse.getTrainingType());
    }

    @Test
    void testConstructorWithParameters() {
        TrainingType trainingType = new TrainingType(1L, "trainingType");
        ActiveTrainerResponse activeTrainerResponse = new ActiveTrainerResponse("username", "firstName", "lastName", trainingType);
        assertEquals("username", activeTrainerResponse.getUsername());
        assertEquals("firstName", activeTrainerResponse.getFirstName());
        assertEquals("lastName", activeTrainerResponse.getLastName());
        assertEquals(trainingType, activeTrainerResponse.getTrainingType());
    }

    @Test
    void testSetterAndGetter() {
        TrainingType trainingType = new TrainingType(1L, "trainingType");
        ActiveTrainerResponse activeTrainerResponse = new ActiveTrainerResponse();
        activeTrainerResponse.setUsername("username");
        activeTrainerResponse.setFirstName("firstName");
        activeTrainerResponse.setLastName("lastName");
        activeTrainerResponse.setTrainingType(trainingType);
        assertEquals("username", activeTrainerResponse.getUsername());
        assertEquals("firstName", activeTrainerResponse.getFirstName());
        assertEquals("lastName", activeTrainerResponse.getLastName());
        assertEquals(trainingType, activeTrainerResponse.getTrainingType());
    }
}
