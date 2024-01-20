package com.example.gymspringboot.dto.request;

import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

public class UpdateTraineeRequestTest {
    @Test
    void testDefaultConstructor() {
        UpdateTraineeRequest updateTraineeRequest = new UpdateTraineeRequest();
        assertNull(updateTraineeRequest.getUsername());
        assertNull(updateTraineeRequest.getFirstName());
        assertNull(updateTraineeRequest.getLastName());
        assertNull(updateTraineeRequest.getAddress());
        assertNull(updateTraineeRequest.getDateOfBirth());
        assertNull(updateTraineeRequest.getActive());
    }

    @Test
    void testConstructorWithParameters() {
        Date date = new Date();
        UpdateTraineeRequest updateTraineeRequest = new UpdateTraineeRequest("username", "firstName", "lastName", date, "address", true);
        assertEquals("username", updateTraineeRequest.getUsername());
        assertEquals("firstName", updateTraineeRequest.getFirstName());
        assertEquals("lastName", updateTraineeRequest.getLastName());
        assertEquals("address", updateTraineeRequest.getAddress());
        assertEquals(date, updateTraineeRequest.getDateOfBirth());
        assertEquals(true, updateTraineeRequest.getActive());
    }

    @Test
    void testSetterAndGetter() {
        Date date = new Date();
        UpdateTraineeRequest updateTraineeRequest = new UpdateTraineeRequest();
        updateTraineeRequest.setUsername("username");
        updateTraineeRequest.setFirstName("firstName");
        updateTraineeRequest.setLastName("lastName");
        updateTraineeRequest.setAddress("address");
        updateTraineeRequest.setDateOfBirth(date);
        updateTraineeRequest.setActive(true);
        assertEquals("username", updateTraineeRequest.getUsername());
        assertEquals("firstName", updateTraineeRequest.getFirstName());
        assertEquals("lastName", updateTraineeRequest.getLastName());
        assertEquals("address", updateTraineeRequest.getAddress());
        assertEquals(date, updateTraineeRequest.getDateOfBirth());
        assertEquals(true, updateTraineeRequest.getActive());
    }
}
