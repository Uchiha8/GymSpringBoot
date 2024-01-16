package com.example.gymspringboot.repository;

import com.example.gymspringboot.domain.Trainee;
import com.example.gymspringboot.domain.Trainer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TrainerRepository extends JpaRepository<Trainer, Long> {

    Trainer findByUserUsername(String username);

    boolean existsByUserUsername(String username);

    boolean deleteByUserUsername(String username);
}
