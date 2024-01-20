package com.example.gymspringboot.dto.response;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class RegistrationResponseTest {
    @Test
    void testDefaultConstructor() {
        RegistrationResponse registrationResponse = new RegistrationResponse();
        assertNull(registrationResponse.getUsername());
        assertNull(registrationResponse.getPassword());
    }

    @Test
    void testConstructorWithParameters() {
        RegistrationResponse registrationResponse = new RegistrationResponse("username", "password");
        assertEquals("username", registrationResponse.getUsername());
        assertEquals("password", registrationResponse.getPassword());
    }

    @Test
    void testSetterAndGetter() {
        RegistrationResponse registrationResponse = new RegistrationResponse();
        registrationResponse.setUsername("username");
        registrationResponse.setPassword("password");
        assertEquals("username", registrationResponse.getUsername());
        assertEquals("password", registrationResponse.getPassword());
    }
}
