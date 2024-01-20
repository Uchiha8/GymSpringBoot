package com.example.gymspringboot.dto.response;

import com.example.gymspringboot.domain.TrainingType;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


public class TrainerProfileResponseTest {
    @Test
    void testDefaultConstructor() {
        TrainerProfileResponse trainerProfileResponse = new TrainerProfileResponse();
        assertNull(trainerProfileResponse.getFirstName());
        assertNull(trainerProfileResponse.getLastName());
        assertNull(trainerProfileResponse.getTrainingType());
        assertNull(trainerProfileResponse.getTrainees());
        assertNull(trainerProfileResponse.getActive());
    }

    @Test
    void testConstructorWithParameters() {
        TrainingType trainingType = new TrainingType(1L, "trainingType");
        TrainerProfileResponse trainerProfileResponse = new TrainerProfileResponse("firstName", "lastName", trainingType, true, List.of(new TraineeListResponse("username", "firstName", "lastName")));
        assertEquals("firstName", trainerProfileResponse.getFirstName());
        assertEquals("lastName", trainerProfileResponse.getLastName());
        assertEquals(trainingType, trainerProfileResponse.getTrainingType());
        assertEquals(true, trainerProfileResponse.getActive());
    }

    @Test
    void testSetterAndGetter() {
        List<TraineeListResponse> trainees = List.of(new TraineeListResponse("username", "firstName", "lastName"));
        TrainingType trainingType = new TrainingType(1L, "trainingType");
        TrainerProfileResponse trainerProfileResponse = new TrainerProfileResponse();
        trainerProfileResponse.setFirstName("firstName");
        trainerProfileResponse.setLastName("lastName");
        trainerProfileResponse.setTrainingType(trainingType);
        trainerProfileResponse.setActive(true);
        trainerProfileResponse.setTrainees(trainees);
        assertEquals("firstName", trainerProfileResponse.getFirstName());
        assertEquals("lastName", trainerProfileResponse.getLastName());
        assertEquals(trainingType, trainerProfileResponse.getTrainingType());
        assertEquals(true, trainerProfileResponse.getActive());
        assertEquals(trainees, trainerProfileResponse.getTrainees());
    }
}
