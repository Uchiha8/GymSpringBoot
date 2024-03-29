package com.example.gymspringboot.repository;

import com.example.gymspringboot.domain.Trainer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface TrainerRepository extends JpaRepository<Trainer, Long> {

    Trainer findByUserUsername(String username);

    boolean existsByUserUsername(String username);

    boolean deleteByUserUsername(String username);

    @Modifying
    @Query("UPDATE Trainer t set t.user.active = :active where t.user.username = :username")
    boolean updateActive(@Param("username") String username, @Param("active") boolean active);
}
