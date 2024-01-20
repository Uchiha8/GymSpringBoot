package com.example.gymspringboot.domain;

import org.junit.jupiter.api.Test;

import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class TraineeTest {

    @Test
    void testDefaultConstructor() {
        Trainee trainee = new Trainee();
        assertNull(trainee.getId());
    }

    @Test
    void testConstructorWithParameters() {
        //prepared data
        Date date = new Date();
        User user = new User(1L, "firstName", "lastName", "username", "password", true);
        //test
        Trainee trainee = new Trainee(1L, date, "address", user, List.of());
        assertEquals(1L, trainee.getId());
        assertEquals(date, trainee.getDateOfBirth());
        assertEquals("address", trainee.getAddress());
        assertEquals(user, trainee.getUser());
        assertEquals(List.of(), trainee.getTrainings());
    }

    @Test
    void testSetterAndGetter() {
        //prepared data
        Date date = new Date();
        User user = new User(1L, "firstName", "lastName", "username", "password", true);
        //test
        Trainee trainee = new Trainee();
        trainee.setId(1L);
        trainee.setDateOfBirth(date);
        trainee.setAddress("address");
        trainee.setUser(user);
        trainee.setTrainings(List.of());
        assertEquals(1L, trainee.getId());
        assertEquals(date, trainee.getDateOfBirth());
        assertEquals("address", trainee.getAddress());
        assertEquals(user, trainee.getUser());
        assertEquals(List.of(), trainee.getTrainings());
    }

}
