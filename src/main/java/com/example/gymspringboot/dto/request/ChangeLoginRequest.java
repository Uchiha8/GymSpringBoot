package com.example.gymspringboot.dto.request;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ChangeLoginRequest {
    @Column(nullable = false)
    private String username;
    @Column(nullable = false)
    private String oldPassword;
    @Column(nullable = false)
    private String newPassword;
}
