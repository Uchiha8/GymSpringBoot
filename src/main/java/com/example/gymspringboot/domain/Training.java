package com.example.gymspringboot.domain;

import jakarta.persistence.*;
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
@Entity
public class Training {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(cascade = CascadeType.ALL)
    private Trainee trainee;
    @ManyToOne(cascade = CascadeType.ALL)
    private Trainer trainer;
    @Column(nullable = false)
    private String trainingName;
    @ManyToOne(cascade = CascadeType.ALL)
    private TrainingType trainingType;
    @Temporal(TemporalType.DATE)
    @Column(nullable = false)
    private Date trainingDate;
    @Column(nullable = false)
    private Duration duration;
}
