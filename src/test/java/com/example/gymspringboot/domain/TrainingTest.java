package com.example.gymspringboot.domain;

import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class TrainingTest {
    @Test
    void testDefaultConstructor() {
        Training training = new Training();
        assertNull(training.getId());
    }

    @Test
    void testConstructorWithParameters() {
        //prepared data
        Date date = new Date();
        User user = new User(1L, "firstName", "lastName", "username", "password", true);
        User user1 = new User(2L, "firstName1", "lastName1", "username1", "password1", true);
        TrainingType trainingType = new TrainingType(1L, "Java");
        Trainee trainee = new Trainee(1L, date, "address", user, List.of());
        Trainer trainer = new Trainer(1L, trainingType, user1, List.of());
        //test
        Training training = new Training(1L, trainee, trainer, "Avengers", trainingType, date, Duration.ofHours(2));
        assertEquals(1L, training.getId());
        assertEquals(trainee, training.getTrainee());
        assertEquals(trainer, training.getTrainer());
        assertEquals("Avengers", training.getTrainingName());
        assertEquals(trainingType, training.getTrainingType());
        assertEquals(date, training.getTrainingDate());
        assertEquals(Duration.ofHours(2), training.getDuration());
    }

    @Test
    void testSetterAndGetter() {
        //prepared data
        Date date = new Date();
        User user = new User(1L, "firstName", "lastName", "username", "password", true);
        User user1 = new User(2L, "firstName1", "lastName1", "username1", "password1", true);
        TrainingType trainingType = new TrainingType(1L, "Java");
        Trainee trainee = new Trainee(1L, date, "address", user, List.of());
        Trainer trainer = new Trainer(1L, trainingType, user1, List.of());
        //test
        Training training = new Training();
        training.setId(1L);
        training.setTrainee(trainee);
        training.setTrainer(trainer);
        training.setTrainingName("Avengers");
        training.setTrainingType(trainingType);
        training.setTrainingDate(date);
        training.setDuration(Duration.ofHours(2));
        assertEquals(1L, training.getId());
        assertEquals(trainee, training.getTrainee());
        assertEquals(trainer, training.getTrainer());
        assertEquals("Avengers", training.getTrainingName());
        assertEquals(trainingType, training.getTrainingType());
        assertEquals(date, training.getTrainingDate());
        assertEquals(Duration.ofHours(2), training.getDuration());
    }

}
