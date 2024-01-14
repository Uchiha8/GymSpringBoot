package com.example.gymspringboot.dto.response;

import com.example.gymspringboot.domain.TrainingType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Duration;
import java.util.Date;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class TrainerTrainingsListResponse {
    private String trainingName;
    private Date trainingDate;
    private TrainingType trainingType;
    private Duration duration;
    private String traineeUsername;
}
