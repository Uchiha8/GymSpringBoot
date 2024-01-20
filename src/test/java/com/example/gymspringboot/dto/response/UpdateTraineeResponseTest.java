package com.example.gymspringboot.dto.response;

import com.example.gymspringboot.domain.TrainingType;
import org.junit.jupiter.api.Test;

import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


public class UpdateTraineeResponseTest {
    @Test
    void testDefaultConstructor() {
        UpdateTraineeResponse updateTraineeResponse = new UpdateTraineeResponse();
        assertNull(updateTraineeResponse.getUsername());
        assertNull(updateTraineeResponse.getFirstName());
        assertNull(updateTraineeResponse.getLastName());
        assertNull(updateTraineeResponse.getAddress());
        assertNull(updateTraineeResponse.getDateOfBirth());
    }

    @Test
    void testConstructorWithParameters() {
        Date date = new Date();
        TrainingType trainingType = new TrainingType(1L, "trainingType");
        List<TrainerListResponse> trainers = List.of(new TrainerListResponse("username", "firstName", "lastName", trainingType));
        UpdateTraineeResponse updateTraineeResponse = new UpdateTraineeResponse("username", "firstName", "lastName", date, "address", true, trainers);
        assertEquals("username", updateTraineeResponse.getUsername());
        assertEquals("firstName", updateTraineeResponse.getFirstName());
        assertEquals("lastName", updateTraineeResponse.getLastName());
        assertEquals("address", updateTraineeResponse.getAddress());
        assertEquals(date, updateTraineeResponse.getDateOfBirth());
        assertEquals(trainers, updateTraineeResponse.getTrainers());
    }

    @Test
    void testSetterAndGetter() {
        Date date = new Date();
        TrainingType trainingType = new TrainingType(1L, "trainingType");
        List<TrainerListResponse> trainers = List.of(new TrainerListResponse("username", "firstName", "lastName", trainingType));
        UpdateTraineeResponse updateTraineeResponse = new UpdateTraineeResponse();
        updateTraineeResponse.setUsername("username");
        updateTraineeResponse.setFirstName("firstName");
        updateTraineeResponse.setLastName("lastName");
        updateTraineeResponse.setAddress("address");
        updateTraineeResponse.setDateOfBirth(date);
        updateTraineeResponse.setTrainers(trainers);
        updateTraineeResponse.setActive(true);
        assertEquals("username", updateTraineeResponse.getUsername());
        assertEquals("firstName", updateTraineeResponse.getFirstName());
        assertEquals("lastName", updateTraineeResponse.getLastName());
        assertEquals("address", updateTraineeResponse.getAddress());
        assertEquals(date, updateTraineeResponse.getDateOfBirth());
        assertEquals(trainers, updateTraineeResponse.getTrainers());
        assertEquals(true, updateTraineeResponse.getActive());
    }
}
