package com.example.gymspringboot.domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TrainingTypeTest {
    @Test
    void testDefaultConstructor() {
        TrainingType trainingType = new TrainingType();
        assertNull(trainingType.getId());
    }

    @Test
    void testConstructorWithParameters() {
        TrainingType trainingType = new TrainingType(1L, "Java");
        assertEquals(1L, trainingType.getId());
        assertEquals("Java", trainingType.getName());
    }

    @Test
    void testSetterAndGetter() {
        TrainingType trainingType = new TrainingType();
        trainingType.setId(1L);
        trainingType.setName("Java");
        assertEquals(1L, trainingType.getId());
        assertEquals("Java", trainingType.getName());
    }
}
