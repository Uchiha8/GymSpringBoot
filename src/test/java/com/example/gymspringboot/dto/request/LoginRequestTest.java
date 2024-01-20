package com.example.gymspringboot.dto.request;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class LoginRequestTest {
    @Test
    void testDefaultConstructor() {
        LoginRequest loginRequest = new LoginRequest();
        assertNull(loginRequest.getUsername());
        assertNull(loginRequest.getPassword());
    }

    @Test
    void testConstructorWithParameters() {
        LoginRequest loginRequest = new LoginRequest("username", "password");
        assertEquals("username", loginRequest.getUsername());
        assertEquals("password", loginRequest.getPassword());
    }

    @Test
    void testSetterAndGetter() {
        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setUsername("username");
        loginRequest.setPassword("password");
        assertEquals("username", loginRequest.getUsername());
        assertEquals("password", loginRequest.getPassword());
    }
}
