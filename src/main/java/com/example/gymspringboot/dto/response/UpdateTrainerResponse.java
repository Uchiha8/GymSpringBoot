package com.example.gymspringboot.dto.response;

import com.example.gymspringboot.domain.TrainingType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UpdateTrainerResponse {
    private String username;
    private String firstName;
    private String lastName;
    private TrainingType trainingType;
    private Boolean active;
    private List<TraineeListResponse> trainees;
}