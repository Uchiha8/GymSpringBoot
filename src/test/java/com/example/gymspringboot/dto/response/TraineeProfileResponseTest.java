package com.example.gymspringboot.dto.response;

import com.example.gymspringboot.domain.TrainingType;
import org.junit.jupiter.api.Test;

import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


public class TraineeProfileResponseTest {
    @Test
    void testDefaultConstructor() {
        TraineeProfileResponse traineeProfileResponse = new TraineeProfileResponse();
        assertNull(traineeProfileResponse.getFirstName());
        assertNull(traineeProfileResponse.getLastName());
        assertNull(traineeProfileResponse.getActive());
        assertNull(traineeProfileResponse.getAddress());
        assertNull(traineeProfileResponse.getDateOfBirth());
        assertNull(traineeProfileResponse.getTrainers());
    }

    @Test
    void testConstructorWithParameters() {
        Date date = new Date();
        TrainingType trainingType = new TrainingType(1L, "trainingType");
        List<TrainerListResponse> trainers = List.of(new TrainerListResponse("username", "firstName", "lastName", trainingType));
        TraineeProfileResponse traineeProfileResponse = new TraineeProfileResponse("firstName", "lastName", date, "address", true, trainers);
        assertEquals("firstName", traineeProfileResponse.getFirstName());
        assertEquals("lastName", traineeProfileResponse.getLastName());
        assertEquals(true, traineeProfileResponse.getActive());
        assertEquals("address", traineeProfileResponse.getAddress());
        assertEquals(date, traineeProfileResponse.getDateOfBirth());
        assertEquals(trainers, traineeProfileResponse.getTrainers());
    }

    @Test
    void testSetterAndGetter() {
        Date date = new Date();
        TrainingType trainingType = new TrainingType(1L, "trainingType");
        List<TrainerListResponse> trainers = List.of(new TrainerListResponse("username", "firstName", "lastName", trainingType));
        TraineeProfileResponse traineeProfileResponse = new TraineeProfileResponse();
        traineeProfileResponse.setFirstName("firstName");
        traineeProfileResponse.setLastName("lastName");
        traineeProfileResponse.setActive(true);
        traineeProfileResponse.setAddress("address");
        traineeProfileResponse.setDateOfBirth(date);
        traineeProfileResponse.setTrainers(trainers);
        assertEquals("firstName", traineeProfileResponse.getFirstName());
        assertEquals("lastName", traineeProfileResponse.getLastName());
        assertEquals(true, traineeProfileResponse.getActive());
        assertEquals("address", traineeProfileResponse.getAddress());
        assertEquals(date, traineeProfileResponse.getDateOfBirth());
        assertEquals(trainers, traineeProfileResponse.getTrainers());
    }
}
