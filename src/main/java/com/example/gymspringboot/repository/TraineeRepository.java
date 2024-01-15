package com.example.gymspringboot.repository;

import com.example.gymspringboot.domain.Trainee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TraineeRepository extends JpaRepository<Trainee, Long> {
    Trainee findByUserUsername(String username);

    boolean existsByUserUsername(String username);

    boolean deleteByUserUsername(String username);
}
