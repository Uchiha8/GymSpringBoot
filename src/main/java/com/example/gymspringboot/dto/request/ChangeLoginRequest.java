package com.example.gymspringboot.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ChangeLoginRequest {
    private String username;
    private String oldPassword;
    private String newPassword;
}
