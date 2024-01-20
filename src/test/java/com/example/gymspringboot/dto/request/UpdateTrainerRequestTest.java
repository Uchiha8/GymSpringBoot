package com.example.gymspringboot.dto.request;

import com.example.gymspringboot.domain.TrainingType;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


public class UpdateTrainerRequestTest {
    @Test
    void testDefaultConstructor() {
        UpdateTrainerRequest updateTrainerRequest = new UpdateTrainerRequest();
        assertNull(updateTrainerRequest.getUsername());
        assertNull(updateTrainerRequest.getFirstName());
        assertNull(updateTrainerRequest.getLastName());
        assertNull(updateTrainerRequest.getTrainingType());
        assertNull(updateTrainerRequest.getActive());
    }

    @Test
    void testConstructorWithParameters() {
        TrainingType trainingType = new TrainingType(1L, "trainingType");
        UpdateTrainerRequest updateTrainerRequest = new UpdateTrainerRequest("username", "firstName", "lastName", trainingType, true);
        assertEquals("username", updateTrainerRequest.getUsername());
        assertEquals("firstName", updateTrainerRequest.getFirstName());
        assertEquals("lastName", updateTrainerRequest.getLastName());
        assertEquals(trainingType, updateTrainerRequest.getTrainingType());
        assertEquals(true, updateTrainerRequest.getActive());
    }

    @Test
    void testSetterAndGetter() {
        TrainingType trainingType = new TrainingType(1L, "trainingType");
        UpdateTrainerRequest updateTrainerRequest = new UpdateTrainerRequest();
        updateTrainerRequest.setUsername("username");
        updateTrainerRequest.setFirstName("firstName");
        updateTrainerRequest.setLastName("lastName");
        updateTrainerRequest.setTrainingType(trainingType);
        updateTrainerRequest.setActive(true);
        assertEquals("username", updateTrainerRequest.getUsername());
        assertEquals("firstName", updateTrainerRequest.getFirstName());
        assertEquals("lastName", updateTrainerRequest.getLastName());
        assertEquals(trainingType, updateTrainerRequest.getTrainingType());
        assertEquals(true, updateTrainerRequest.getActive());
    }
}
