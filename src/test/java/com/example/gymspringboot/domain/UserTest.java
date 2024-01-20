package com.example.gymspringboot.domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class UserTest {

    @Test
    void testDefaultConstructor() {
        User user = new User();
        assertNull(user.getId());
    }

    @Test
    void testConstructorWithParameters() {
        User user = new User(1L, "firstName", "lastName", "username", "password", true);
        assertEquals(1L, user.getId());
        assertEquals("firstName", user.getFirstName());
        assertEquals("lastName", user.getLastName());
        assertEquals("username", user.getUsername());
        assertEquals("password", user.getPassword());
        assertEquals(true, user.getActive());
    }

    @Test
    void testSetterAndGetter() {
        User user = new User();
        user.setId(1L);
        user.setFirstName("firstName");
        user.setLastName("lastName");
        user.setUsername("username");
        user.setPassword("password");
        user.setActive(true);
        assertEquals(1L, user.getId());
        assertEquals("firstName", user.getFirstName());
        assertEquals("lastName", user.getLastName());
        assertEquals("username", user.getUsername());
        assertEquals("password", user.getPassword());
        assertEquals(true, user.getActive());
    }

}
