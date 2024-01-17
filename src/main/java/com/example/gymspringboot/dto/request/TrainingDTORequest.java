package com.example.gymspringboot.dto.request;

import jakarta.persistence.Column;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
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
public class TrainingDTORequest {
    @Column(nullable = false)
    private String traineeUsername;
    @Column(nullable = false)
    private String trainerUsername;
    @Column(nullable = false)
    private String trainingName;
    @Column(nullable = false)
    @Temporal(TemporalType.DATE)
    private Date trainingDate;
    @Column(nullable = false)
    private Duration duration;
}

