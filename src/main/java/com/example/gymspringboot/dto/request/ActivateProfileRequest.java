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
public class ActivateProfileRequest {
    @Column(nullable = false)
    private String username;
    @Column(nullable = false)
    private Boolean active;
}
