package com.example.gymspringboot.service;

import com.example.gymspringboot.domain.User;
import com.example.gymspringboot.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
public class UserServiceTest {
    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testFindByUserName() {
        // Given
        String userName = "john.dev";
        User expectedUser = new User(1L, "john", "dev", "john.dev", "password", true);
        when(userRepository.findByUsername(userName)).thenReturn(expectedUser);

        // When
        User result = userService.findByUserName(userName);

        // Then
        assertEquals(expectedUser, result);
        verify(userRepository, times(1)).findByUsername(userName);
    }

    @Test
    public void testLogin() {
        // Given
        String userName = "testUser";
        String password = "password";
        User user = new User(13L, "firstName", "lastName", userName, password, true);
        when(userRepository.findByUsername(userName)).thenReturn(user);
        // When
        boolean result = userService.login(userName, password);
        // Then
        assertTrue(result);
        verify(userRepository, times(1)).findByUsername(userName);
    }

    @Test
    public void testChangePassword() {
        // Given
        String userName = "john.ali";
        String oldPassword = "oldPassword";
        String newPassword = "newPassword";
        User user = new User(13L, "John", "Ali", userName, oldPassword, true);
        when(userRepository.findByUsername(userName)).thenReturn(user);
        // When
        boolean result = userService.changePassword(userName, oldPassword, newPassword);
        // Then
        assertTrue(result);
        assertEquals(newPassword, user.getPassword());
        verify(userRepository, times(1)).save(user);
        verify(userRepository, times(1)).findByUsername(userName);
    }

    @Test
    public void testSave() {
        // Given
        User user = new User(12L, "firstName", "lastName", null, null, true);
        // When
        when(userRepository.save(user)).thenReturn(user);
        User result = userService.save(user);
        // Then
        assertNotNull(result.getUsername());
        assertEquals("firstname.lastname", result.getUsername());
        assertNotNull(result.getPassword());
        verify(userRepository, times(1)).save(user);
    }

    @Test
    public void testExistsByUsername() {
        // Given
        String userName = "user.name";
        when(userRepository.existsByUsername(userName)).thenReturn(true);
        // When
        boolean result = userService.existsByUsername(userName);
        // Then
        assertTrue(result);
        verify(userRepository, times(1)).existsByUsername(userName);
    }

    @Test
    public void testUsernameGenerator() {
        // Given
        String firstName = "John";
        String lastName = "Doe";
        when(userRepository.existsByUsername(anyString())).thenReturn(false);

        // When
        String result = userService.usernameGenerator(firstName, lastName);

        // Then
        assertEquals("john.doe", result);
        verify(userRepository, times(1)).existsByUsername(result);
    }

    @Test
    public void testPasswordGenerator() {
        // When
        String result = userService.passwordGenerator();
        // Then
        assertNotNull(result);
        assertEquals(10, result.length());
    }



}
