package com.example.gymspringboot.service;

import com.example.gymspringboot.domain.*;
import com.example.gymspringboot.dto.request.ActivateProfileRequest;
import com.example.gymspringboot.dto.request.TraineeRegistrationRequest;
import com.example.gymspringboot.dto.request.TraineeTrainingsListRequest;
import com.example.gymspringboot.dto.request.UpdateTraineeRequest;
import com.example.gymspringboot.dto.response.RegistrationResponse;
import com.example.gymspringboot.dto.response.TraineeProfileResponse;
import com.example.gymspringboot.dto.response.TraineeTrainingsListResponse;
import com.example.gymspringboot.dto.response.UpdateTraineeResponse;
import com.example.gymspringboot.repository.TraineeRepository;
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

public class TraineeServiceTest {
    @Mock
    private TraineeRepository traineeRepository;
    @Mock
    private UserService userService;
    @InjectMocks
    private TraineeService traineeService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testRegister() {
        // Given
        Date dateOfBirth = new Date();
        TraineeRegistrationRequest request = new TraineeRegistrationRequest("John", "Doe", dateOfBirth, "Address");
        User user = new User(1L, "John", "Doe", "john.doe", "password", false);
        Trainee trainee = new Trainee(1L, dateOfBirth, "Address", user, new ArrayList<>());
        when(userService.save(any(User.class))).thenReturn(user);
        when(traineeRepository.save(any(Trainee.class))).thenReturn(trainee);

        // When
        RegistrationResponse response = traineeService.register(request);

        // Then
        assertNotNull(response);
        assertEquals("john.doe", response.getUsername());
        assertEquals("password", response.getPassword());
        verify(userService, times(1)).save(any(User.class));
        verify(traineeRepository, times(1)).save(any(Trainee.class));
    }

    @Test
    public void testFindByUserName() {
        // Given
        Date dateOfBirth = new Date();
        String userName = "john.doe";
        Trainee trainee = new Trainee(1L, dateOfBirth, "Address", new User(1L, "John", "Doe", userName, "password", true), new ArrayList<>());
        when(traineeRepository.findByUserUsername(userName)).thenReturn(trainee);

        // When
        TraineeProfileResponse response = traineeService.findByUserName(userName);

        // Then
        assertNotNull(response);
        assertEquals("John", response.getFirstName());
        assertEquals("Doe", response.getLastName());
        assertTrue(response.getActive());
        assertEquals(0, response.getTrainers().size());
        verify(traineeRepository, times(1)).findByUserUsername(userName);
    }

    @Test
    public void testExistsByUserName() {
        // Given
        String userName = "john.doe";
        when(traineeRepository.existsByUserUsername(userName)).thenReturn(true);
        // When
        boolean result = traineeService.existsByUserName(userName);
        // Then
        assertTrue(result);
        verify(traineeRepository, times(1)).existsByUserUsername(userName);
    }

    @Test
    public void testUpdateTrainee() {
        // Given
        Date dateOfBirth = new Date();
        String userName = "john.doe";
        String firstName = "John";
        String lastName = "Doe";
        String address = "Address";
        boolean active = true;
        Trainee trainee = new Trainee(1L, dateOfBirth, address, new User(1L, firstName, lastName, userName, "password", true), new ArrayList<>());
        UpdateTraineeRequest request = new UpdateTraineeRequest(userName, firstName, lastName, dateOfBirth, address, active);
        when(traineeRepository.findByUserUsername(userName)).thenReturn(trainee);
        when(userService.usernameGenerator(firstName, lastName)).thenReturn(userName);
        // When
        UpdateTraineeResponse response = traineeService.updateTrainee(request);
        // Then
        assertNotNull(response);
        assertEquals(userName, response.getUsername());
        assertEquals(firstName, response.getFirstName());
        assertEquals(lastName, response.getLastName());
        assertEquals(dateOfBirth, response.getDateOfBirth());
        assertEquals(address, response.getAddress());
        assertEquals(active, response.getActive());
        assertEquals(0, response.getTrainers().size());
        verify(traineeRepository, times(1)).findByUserUsername(userName);
        verify(userService, times(1)).usernameGenerator(firstName, lastName);
        verify(traineeRepository, times(1)).save(trainee);
    }

    @Test
    public void testDeleteTrainee() {
        // Given
        String username = "john.doe";
        Trainee trainee = new Trainee(1L, null, null, new User(1L, "John", "Doe", username, "password", true), null);
        // Stubbing
        when(traineeRepository.findByUserUsername(username)).thenReturn(trainee);
        // When
        traineeService.deleteTrainee(username);
        // Additional verifications
        verify(traineeRepository, times(1)).findByUserUsername(username);
        verify(traineeRepository, times(1)).existsByUserUsername(username);
    }

    @Test
    void readTraineeTrainingsList() {
        Date dateOfBirth = new Date();
        Trainee trainee = new Trainee(1L, dateOfBirth, "Main street 13", new User(1L, "John", "Doe", "john.doe", "password", true), null);
        Trainer trainer = new Trainer(1L, new TrainingType(1L, "trainingType"), new User(2L, "Jane", "Doe", "jane.doe", "password", true), null);
        Training training = new Training(1L, trainee, trainer, "Avengers", new TrainingType(1L, "Java"), new Date(), Duration.ofHours(2));
        trainee.setTrainings(List.of(training));
        trainer.setTrainings(List.of(training));
        TraineeTrainingsListRequest request = new TraineeTrainingsListRequest("john.doe", new Date(), new Date(), "jane.doe", "Avengers");
        when(traineeRepository.findByUserUsername("john.doe")).thenReturn(trainee);
        List<TraineeTrainingsListResponse> response = traineeService.readTraineeTrainingsList(request);
        assertEquals(1, response.size());
        assertEquals("Avengers", response.get(0).getTrainingName());
        assertEquals("jane.doe", response.get(0).getTrainerUsername());
        assertEquals(Duration.ofHours(2), response.get(0).getDuration());
    }

    @Test
    public void testActivateDeactivateTrainee() {
        // Given
        String username = "john.doe";
        boolean activeStatus = true;
        ActivateProfileRequest request = new ActivateProfileRequest(username, activeStatus);
        Trainee trainee = new Trainee(1L, null, null, new User(1L, "John", "Doe", username, "password", false), null);
        // Stubbing
        when(traineeRepository.findByUserUsername(username)).thenReturn(trainee);
        // When
        traineeService.activateDeactivateTrainee(request);
        // Additional verifications
        verify(traineeRepository, times(1)).findByUserUsername(username);
        verify(traineeRepository, times(1)).save(trainee);
    }
}
