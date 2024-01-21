package com.example.gymspringboot.service;

import com.example.gymspringboot.domain.TrainingType;
import com.example.gymspringboot.repository.TrainingTypeRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class TrainingTypeTest {
    @Mock
    private TrainingTypeRepository trainingTypeRepository;

    @InjectMocks
    private TrainingTypeService trainingTypeService;

    @Test
    public void testSave() {
        // Given
        TrainingType trainingType = new TrainingType(1L, "Type1");
        when(trainingTypeRepository.save(trainingType)).thenReturn(trainingType);

        // When
        TrainingType result = trainingTypeService.save(trainingType);

        // Then
        assertNotNull(result);
        assertEquals(1L, result.getId());
        assertEquals("Type1", result.getName());
        verify(trainingTypeRepository, times(1)).save(trainingType);
    }

    @Test
    public void testFindByTrainingTypeName() {
        // Given
        String trainingTypeName = "Type1";
        TrainingType trainingType = new TrainingType(1L, trainingTypeName);
        when(trainingTypeRepository.findByName(trainingTypeName)).thenReturn(trainingType);

        // When
        TrainingType result = trainingTypeService.findByTrainingTypeName(trainingTypeName);

        // Then
        assertNotNull(result);
        assertEquals(1L, result.getId());
        assertEquals(trainingTypeName, result.getName());
        verify(trainingTypeRepository, times(1)).findByName(trainingTypeName);
    }

    @Test
    public void testFindAll() {
        // Given
        List<TrainingType> trainingTypes = List.of(
                new TrainingType(1L, "Type1"),
                new TrainingType(2L, "Type2"gi)
        );
        when(trainingTypeRepository.findAll()).thenReturn(trainingTypes);

        // When
        List<TrainingType> result = trainingTypeService.findAll();

        // Then
        assertNotNull(result);
        assertEquals(2, result.size());
        verify(trainingTypeRepository, times(1)).findAll();
    }
}
