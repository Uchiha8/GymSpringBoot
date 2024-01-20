package com.example.gymspringboot.dto.request;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class ChangeLoginRequestTest {
    @Test
    void testDefaultConstructor() {
        ChangeLoginRequest changeLoginRequest = new ChangeLoginRequest();
        assertNull(changeLoginRequest.getUsername());
        assertNull(changeLoginRequest.getOldPassword());
        assertNull(changeLoginRequest.getNewPassword());
    }

    @Test
    void testConstructorWithParameters() {
        ChangeLoginRequest changeLoginRequest = new ChangeLoginRequest("username", "oldPassword", "newPassword");
        assertEquals("username", changeLoginRequest.getUsername());
        assertEquals("oldPassword", changeLoginRequest.getOldPassword());
        assertEquals("newPassword", changeLoginRequest.getNewPassword());
    }

    @Test
    void testSetterAndGetter() {
        ChangeLoginRequest changeLoginRequest = new ChangeLoginRequest();
        changeLoginRequest.setUsername("username");
        changeLoginRequest.setOldPassword("oldPassword");
        changeLoginRequest.setNewPassword("newPassword");
        assertEquals("username", changeLoginRequest.getUsername());
        assertEquals("oldPassword", changeLoginRequest.getOldPassword());
        assertEquals("newPassword", changeLoginRequest.getNewPassword());
    }
}
