package com.example.gymspringboot.dto.request;

import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;


public class TraineeRegistrationRequestTest {
    @Test
    void testDefaultConstructor() {
        TraineeRegistrationRequest traineeRegistrationRequest = new TraineeRegistrationRequest();
        assertNull(traineeRegistrationRequest.getFirstName());
        assertNull(traineeRegistrationRequest.getLastName());
        assertNull(traineeRegistrationRequest.getAddress());
        assertNull(traineeRegistrationRequest.getDateOfBirth());
    }

    @Test
    void testConstructorWithParameters() {
        Date date = new Date();
        TraineeRegistrationRequest traineeRegistrationRequest = new TraineeRegistrationRequest("firstName", "lastName", date, "address");
        assertEquals("firstName", traineeRegistrationRequest.getFirstName());
        assertEquals("lastName", traineeRegistrationRequest.getLastName());
        assertEquals("address", traineeRegistrationRequest.getAddress());
        assertEquals(date, traineeRegistrationRequest.getDateOfBirth());
    }

    @Test
    void testSetterAndGetter() {
        Date date = new Date();
        TraineeRegistrationRequest traineeRegistrationRequest = new TraineeRegistrationRequest();
        traineeRegistrationRequest.setFirstName("firstName");
        traineeRegistrationRequest.setLastName("lastName");
        traineeRegistrationRequest.setAddress("address");
        traineeRegistrationRequest.setDateOfBirth(date);
        assertEquals("firstName", traineeRegistrationRequest.getFirstName());
        assertEquals("lastName", traineeRegistrationRequest.getLastName());
        assertEquals("address", traineeRegistrationRequest.getAddress());
        assertEquals(date, traineeRegistrationRequest.getDateOfBirth());
    }
}
