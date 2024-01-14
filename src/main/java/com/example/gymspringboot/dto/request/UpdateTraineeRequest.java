package com.example.gymspringboot.dto.request;

import jakarta.persistence.Column;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.*;

import java.util.Date;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UpdateTraineeRequest {
    @Column(nullable = false)
    private String username;
    @Column(nullable = false)
    private String firstName;
    @Column(nullable = false)
    private String lastName;
    @Temporal(TemporalType.DATE)
    private Date dateOfBirth;
    private String address;
    @Column(nullable = false)
    private Boolean active;
}
