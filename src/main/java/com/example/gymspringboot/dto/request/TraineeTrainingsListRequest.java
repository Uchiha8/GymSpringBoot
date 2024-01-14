package com.example.gymspringboot.dto.request;

import lombok.*;

import java.util.Date;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class TraineeTrainingsListRequest {
    private String username;
    private Date periodFrom;
    private Date periodTo;
    private String trainerUsername;
    private String trainingType;
}
