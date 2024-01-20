package com.example.gymspringboot.dto.request;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TrainerRegistrationRequestTest {
    @Test
    void testDefaultConstructor() {
        TrainerRegistrationRequest trainerRegistrationRequest = new TrainerRegistrationRequest();
        assertNull(trainerRegistrationRequest.getFirstName());
        assertNull(trainerRegistrationRequest.getLastName());
        assertNull(trainerRegistrationRequest.getTrainingType());
    }

    @Test
    void testConstructorWithParameters() {
        TrainerRegistrationRequest trainerRegistrationRequest = new TrainerRegistrationRequest("firstName", "lastName", "trainingType");
        assertEquals("firstName", trainerRegistrationRequest.getFirstName());
        assertEquals("lastName", trainerRegistrationRequest.getLastName());
        assertEquals("trainingType", trainerRegistrationRequest.getTrainingType());
    }

    @Test
    void testSetterAndGetter() {
        TrainerRegistrationRequest trainerRegistrationRequest = new TrainerRegistrationRequest();
        trainerRegistrationRequest.setFirstName("firstName");
        trainerRegistrationRequest.setLastName("lastName");
        trainerRegistrationRequest.setTrainingType("trainingType");
        assertEquals("firstName", trainerRegistrationRequest.getFirstName());
        assertEquals("lastName", trainerRegistrationRequest.getLastName());
        assertEquals("trainingType", trainerRegistrationRequest.getTrainingType());
    }
}
