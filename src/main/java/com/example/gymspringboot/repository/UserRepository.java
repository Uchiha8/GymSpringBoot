package com.example.gymspringboot.repository;

import com.example.gymspringboot.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String userName);
    boolean existsByUsername(String userName);
}
