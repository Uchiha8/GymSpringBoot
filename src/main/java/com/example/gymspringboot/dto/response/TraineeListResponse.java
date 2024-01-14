package com.example.gymspringboot.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class TraineeListResponse {
    private String username;
    private String firstName;
    private String lastName;
}
