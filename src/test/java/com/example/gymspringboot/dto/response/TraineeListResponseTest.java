package com.example.gymspringboot.dto.response;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TraineeListResponseTest {
    @Test
    void testDefaultConstructor() {
        TraineeListResponse traineeListResponse = new TraineeListResponse();
        assertNull(traineeListResponse.getUsername());
        assertNull(traineeListResponse.getFirstName());
        assertNull(traineeListResponse.getLastName());
    }

    @Test
    void testConstructorWithParameters() {
        TraineeListResponse traineeListResponse = new TraineeListResponse("username", "firstName", "lastName");
        assertEquals("username", traineeListResponse.getUsername());
        assertEquals("firstName", traineeListResponse.getFirstName());
        assertEquals("lastName", traineeListResponse.getLastName());
    }

    @Test
    void testSetterAndGetter() {
        TraineeListResponse traineeListResponse = new TraineeListResponse();
        traineeListResponse.setUsername("username");
        traineeListResponse.setFirstName("firstName");
        traineeListResponse.setLastName("lastName");
        assertEquals("username", traineeListResponse.getUsername());
        assertEquals("firstName", traineeListResponse.getFirstName());
        assertEquals("lastName", traineeListResponse.getLastName());
    }
}
