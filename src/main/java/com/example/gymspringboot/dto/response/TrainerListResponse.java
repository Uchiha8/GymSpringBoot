package com.example.gymspringboot.dto.response;

import com.example.gymspringboot.domain.TrainingType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class TrainerListResponse {
    private String username;
    private String firstName;
    private String lastName;
    private TrainingType trainingType;
}
