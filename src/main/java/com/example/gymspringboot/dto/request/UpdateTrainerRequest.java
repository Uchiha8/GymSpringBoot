package com.example.gymspringboot.dto.request;

import com.example.gymspringboot.domain.TrainingType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UpdateTrainerRequest {
    private String username;
    private String firstName;
    private String lastName;
    private TrainingType trainingType;
    private Boolean active;
}
