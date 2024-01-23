package com.example.gymspringboot.repository;

import com.example.gymspringboot.domain.Trainee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface TraineeRepository extends JpaRepository<Trainee, Long> {
    Trainee findByUserUsername(String username);

    boolean existsByUserUsername(String username);
    @Modifying
    @Query("DELETE FROM Trainee t where t.user.username = :username")
    boolean deleteByUserUsername(@Param("username") String username);

    @Modifying
    @Query("UPDATE Trainee t set t.user.active = :active where t.user.username = :username")
    boolean updateActive(@Param("username") String username, @Param("active") boolean active);

}
