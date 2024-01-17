package com.example.gymspringboot.dto.request;

import jakarta.persistence.Column;
import lombok.*;

import java.util.Date;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class TraineeTrainingsListRequest {
    @Column(nullable = false)
    private String username;
    private Date periodFrom;
    private Date periodTo;
    private String trainerUsername;
    private String trainingType;
}
