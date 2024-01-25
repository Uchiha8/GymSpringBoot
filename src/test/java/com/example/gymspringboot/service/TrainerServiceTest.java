package com.example.gymspringboot.service;

import com.example.gymspringboot.domain.*;
import com.example.gymspringboot.dto.request.ActivateProfileRequest;
import com.example.gymspringboot.dto.request.TrainerRegistrationRequest;
import com.example.gymspringboot.dto.request.TrainerTrainingListRequest;
import com.example.gymspringboot.dto.request.UpdateTrainerRequest;
import com.example.gymspringboot.dto.response.*;
import com.example.gymspringboot.repository.TrainerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class TrainerServiceTest {
    @Mock
    private TrainerRepository trainerRepository;

    @Mock
    private UserService userService;
    @Mock
    private TrainingTypeService trainingTypeService;

    @InjectMocks
    private TrainerService trainerService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testRegister() {
        // Given
        TrainerRegistrationRequest request = new TrainerRegistrationRequest("John", "Doe", "Type1");
        TrainingType trainingType = new TrainingType(1L, "Type1");
        when(userService.save(any(User.class))).thenReturn(new User(1L, "John", "Doe", "john.doe", "password", false));
        when(trainingTypeService.findByTrainingTypeName("Type1")).thenReturn(trainingType);
        when(trainerRepository.save(any(Trainer.class))).thenReturn(new Trainer(1L, trainingType, new User(1L, "John", "Doe", "john.doe", "password", false), new ArrayList<>()));

        // When
        RegistrationResponse response = trainerService.register(request);

        // Then
        assertNotNull(response);
        assertNotNull(response.getUsername());
        assertNotNull(response.getPassword());
        verify(userService, times(1)).save(any(User.class));
        verify(trainingTypeService, times(1)).findByTrainingTypeName("Type1");
        verify(trainerRepository, times(1)).save(any(Trainer.class));
    }

    @Test
    public void testFindByUserName() {
        // Given
        String userName = "john.doe";
        TrainingType trainingType = new TrainingType(1L, "Type1");
        Trainer trainer = new Trainer(1L, trainingType, new User(1L, "John", "Doe", userName, "password", true), new ArrayList<>());
        when(trainerRepository.findByUserUsername(userName)).thenReturn(trainer);

        // When
        TrainerProfileResponse response = trainerService.findByUserName(userName);

        // Then
        assertNotNull(response);
        assertEquals("John", response.getFirstName());
        assertEquals("Doe", response.getLastName());
        assertEquals(trainingType, response.getTrainingType());
        assertTrue(response.getActive());
        assertTrue(response.getTrainees().isEmpty());
        verify(trainerRepository, times(1)).findByUserUsername(userName);
    }

    @Test
    public void testExistsByUserName() {
        // Given
        String userName = "john.doe";
        when(trainerRepository.existsByUserUsername(userName)).thenReturn(true);
        // When
        boolean result = trainerService.existsByUserName(userName);
        // Then
        assertTrue(result);
        verify(trainerRepository, times(1)).existsByUserUsername(userName);
    }

    @Test
    public void testReadTrainerTrainingsList() {
        // Given
        Date periodFrom = new Date();
        Date periodTo = new Date();
        Trainer trainer = new Trainer(1L, new TrainingType(1L, "Type1"), new User(1L, "John", "Liam", "john.doe", "password", true), new ArrayList<>());
        Trainee trainee = new Trainee(1L, new Date(), "Address", new User(2L, "John", "Doe", "john.doe", "password", true), new ArrayList<>());
        TrainerTrainingListRequest request = new TrainerTrainingListRequest("john.liam", periodFrom, periodTo, "john.doe");
        TrainingType trainingType = new TrainingType(1L, "Type1");
        Training training = new Training(1L, trainee, trainer, "Unset", trainingType, periodFrom, Duration.ofHours(2));
        trainer.getTrainings().add(training);
        when(trainerRepository.findByUserUsername("john.liam")).thenReturn(trainer);

        // When
        List<TrainerTrainingsListResponse> responseList = trainerService.readTrainerTrainingsList(request);

        // Then
        assertNotNull(responseList);
        assertEquals("Unset", responseList.get(0).getTrainingName());
        assertEquals(1, responseList.size());
        verify(trainerRepository, times(1)).findByUserUsername("john.liam");
    }

    @Test
    public void testUpdate() {
        // Given
        TrainingType trainingType = new TrainingType(1L, "Type1");
        UpdateTrainerRequest request = new UpdateTrainerRequest("john.doe", "John", "Doe", trainingType, true);
        Trainer trainer = new Trainer(1L, trainingType, new User(1L, "John", "Doe", "john.doe", "password", true), new ArrayList<>());
        List<TraineeListResponse> traineeListResponses = new ArrayList<>();
        when(trainerRepository.findByUserUsername("john.doe")).thenReturn(trainer);
        when(trainingTypeService.findByTrainingTypeName("Type1")).thenReturn(trainingType);
        when(trainerRepository.save(trainer)).thenReturn(trainer);

        // When
        UpdateTrainerResponse response = trainerService.update(request);

        // Then
        assertNotNull(response);
        assertEquals("john.doe", response.getUsername());
        assertEquals("John", response.getFirstName());
        assertEquals("Doe", response.getLastName());
        assertEquals(trainingType, response.getTrainingType());
        assertTrue(response.getActive());
        assertTrue(response.getTrainees().isEmpty());
        verify(trainerRepository, times(1)).findByUserUsername("john.doe");
        verify(trainingTypeService, times(1)).findByTrainingTypeName("Type1");
        verify(trainerRepository, times(1)).save(trainer);
    }

    @Test
    public void testDelete() {
        // Given
        String userName = "john.doe";
        when(trainerRepository.existsByUserUsername(userName)).thenReturn(true);
        when(trainerRepository.deleteByUserUsername(userName)).thenReturn(true);

        // When
        trainerService.delete(userName);

        // Then
        verify(trainerRepository, times(1)).existsByUserUsername(userName);
        verify(trainerRepository, times(1)).deleteByUserUsername(userName);
    }

    @Test
    public void testFindAllActiveTrainersNotAssignedTrainee() {
        // Given
        String username = "traineeUsername";
        List<Trainer> trainers = List.of(
                new Trainer(1L, new TrainingType(1L, "Type1"), new User(1L, "John", "Doe", "john.doe", "password", true), new ArrayList<>()),
                new Trainer(2L, new TrainingType(2L, "Type2"), new User(2L, "Jane", "Smith", "jane.smith", "password", true), new ArrayList<>())
        );
        when(trainerRepository.findAll()).thenReturn(trainers);

        // When
        List<ActiveTrainerResponse> responseList = trainerService.findAllActiveTrainersNotAssignedTrainee(username);

        // Then
        assertNotNull(responseList);
        assertEquals(2, responseList.size());
        verify(trainerRepository, times(1)).findAll();
    }

    @Test
    public void testActivateDeactivateTrainer() {
        // Given
        ActivateProfileRequest request = new ActivateProfileRequest("john.doe", true);
        when(trainerRepository.existsByUserUsername("john.doe")).thenReturn(true);
        when(trainerRepository.updateActive("john.doe", true)).thenReturn(true);

        // When
        trainerService.activateDeactivateTrainer(request);

        // Then
        verify(trainerRepository, times(1)).existsByUserUsername("john.doe");
        verify(trainerRepository, times(1)).updateActive("john.doe", true);
    }
}
