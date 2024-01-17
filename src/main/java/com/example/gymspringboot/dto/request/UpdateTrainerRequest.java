package com.example.gymspringboot.dto.request;

import com.example.gymspringboot.domain.TrainingType;
import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UpdateTrainerRequest {
    @Column(nullable = false)
    private String username;
    @Column(nullable = false)
    private String firstName;
    @Column(nullable = false)
    private String lastName;
    @Column(nullable = false)
    private TrainingType trainingType;
    @Column(nullable = false)
    private Boolean active;
}
