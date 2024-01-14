package com.example.gymspringboot.dto.response;

import com.example.gymspringboot.domain.TrainingType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class TrainerProfileResponse {
    private String firstName;
    private String lastName;
    private TrainingType trainingType;
    private Boolean active;
    private List<TraineeListResponse> trainees;
}
