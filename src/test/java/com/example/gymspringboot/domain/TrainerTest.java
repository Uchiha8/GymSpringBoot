package com.example.gymspringboot.domain;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class TrainerTest {

    @Test
    void testDefaultConstructor() {
        Trainer trainer = new Trainer();
        assertNull(trainer.getId());
    }

    @Test
    void testConstructorWithParameters() {
        //prepared data
        TrainingType trainingType = new TrainingType(1L, "Java");
        User user = new User(1L, "firstName", "lastName", "username", "password", true);
        //test
        Trainer trainer = new Trainer(1L, trainingType, user, List.of());
        assertEquals(1L, trainer.getId());
        assertEquals(trainingType, trainer.getTrainingType());
        assertEquals(user, trainer.getUser());
        assertEquals(List.of(), trainer.getTrainings());
    }

    @Test
    void testSetterAndGetter() {
        //prepared data
        TrainingType trainingType = new TrainingType(1L, "Java");
        User user = new User(1L, "firstName", "lastName", "username", "password", true);
        //test
        Trainer trainer = new Trainer();
        trainer.setId(1L);
        trainer.setTrainingType(trainingType);
        trainer.setUser(user);
        trainer.setTrainings(List.of());
        assertEquals(1L, trainer.getId());
        assertEquals(trainingType, trainer.getTrainingType());
        assertEquals(user, trainer.getUser());
        assertEquals(List.of(), trainer.getTrainings());
    }

}
