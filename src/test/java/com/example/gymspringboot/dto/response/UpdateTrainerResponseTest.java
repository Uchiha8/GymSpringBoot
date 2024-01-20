package com.example.gymspringboot.dto.response;

import com.example.gymspringboot.domain.TrainingType;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class UpdateTrainerResponseTest {
    @Test
    void testDefaultConstructor() {
        UpdateTrainerResponse updateTrainerResponse = new UpdateTrainerResponse();
        assertNull(updateTrainerResponse.getFirstName());
        assertNull(updateTrainerResponse.getLastName());
        assertNull(updateTrainerResponse.getTrainingType());
        assertNull(updateTrainerResponse.getTrainees());
        assertNull(updateTrainerResponse.getActive());
    }

    @Test
    void testConstructorWithParameters() {
        TrainingType trainingType = new TrainingType(1L, "trainingType");
        List<TraineeListResponse> trainees = List.of(new TraineeListResponse("username", "firstName", "lastName"));
        UpdateTrainerResponse updateTrainerResponse = new UpdateTrainerResponse("username", "firstName", "lastName", trainingType, true, trainees);
        assertEquals("username", updateTrainerResponse.getUsername());
        assertEquals("firstName", updateTrainerResponse.getFirstName());
        assertEquals("lastName", updateTrainerResponse.getLastName());
        assertEquals(trainingType, updateTrainerResponse.getTrainingType());
        assertEquals(true, updateTrainerResponse.getActive());
        assertEquals(trainees, updateTrainerResponse.getTrainees());
    }

    @Test
    void testSetterAndGetter() {
        List<TraineeListResponse> trainees = List.of(new TraineeListResponse("username", "firstName", "lastName"));
        TrainingType trainingType = new TrainingType(1L, "trainingType");
        UpdateTrainerResponse updateTrainerResponse = new UpdateTrainerResponse();
        updateTrainerResponse.setUsername("username");
        updateTrainerResponse.setFirstName("firstName");
        updateTrainerResponse.setLastName("lastName");
        updateTrainerResponse.setTrainingType(trainingType);
        updateTrainerResponse.setActive(true);
        updateTrainerResponse.setTrainees(trainees);
        assertEquals("username", updateTrainerResponse.getUsername());
        assertEquals("firstName", updateTrainerResponse.getFirstName());
        assertEquals("lastName", updateTrainerResponse.getLastName());
        assertEquals(trainingType, updateTrainerResponse.getTrainingType());
        assertEquals(true, updateTrainerResponse.getActive());
        assertEquals(trainees, updateTrainerResponse.getTrainees());
    }
}
