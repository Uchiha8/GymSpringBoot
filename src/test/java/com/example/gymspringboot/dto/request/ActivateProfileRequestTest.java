package com.example.gymspringboot.dto.request;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ActivateProfileRequestTest {
    @Test
    void testDefaultConstructor() {
        ActivateProfileRequest activateProfileRequest = new ActivateProfileRequest();
        assertNull(activateProfileRequest.getUsername());
        assertNull(activateProfileRequest.getActive());
    }

    @Test
    void testConstructorWithParameters() {
        ActivateProfileRequest activateProfileRequest = new ActivateProfileRequest("username", true);
        assertEquals("username", activateProfileRequest.getUsername());
        assertEquals(true, activateProfileRequest.getActive());
    }

    @Test
    void testSetterAndGetter() {
        ActivateProfileRequest activateProfileRequest = new ActivateProfileRequest();
        activateProfileRequest.setUsername("username");
        activateProfileRequest.setActive(true);
        assertEquals("username", activateProfileRequest.getUsername());
        assertEquals(true, activateProfileRequest.getActive());
    }
}
