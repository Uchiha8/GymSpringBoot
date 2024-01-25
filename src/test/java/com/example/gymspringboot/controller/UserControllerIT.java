package com.example.gymspringboot.controller;

import com.example.gymspringboot.GymSpringBootApplication;
import com.example.gymspringboot.dto.request.ChangeLoginRequest;
import com.example.gymspringboot.dto.request.LoginRequest;
import com.example.gymspringboot.utils.TestUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = GymSpringBootApplication.class)
//@Sql(scripts = "/data.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_CLASS)
public class UserControllerIT {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private UserController userController;

    @BeforeEach
    void setUp() throws Exception {
        mockMvc = MockMvcBuilders.standaloneSetup(userController)
                .setCustomArgumentResolvers(new PageableHandlerMethodArgumentResolver())
                .build();
    }

    @Test
    public void login_Successful() throws Exception {
        // Given
        LoginRequest loginRequest = new LoginRequest("ali.val", "password");
        // When and Then
        mockMvc.perform(get("/api/v1/user/login")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(TestUtils.convertObjectToJsonBytes(loginRequest)))
                .andExpect(status().isOk())
                .andExpect(content().string("Login successful"));
    }

    @Test
    public void updatePassword() throws Exception {
        ChangeLoginRequest changeLoginRequest = new ChangeLoginRequest("ahmad.ahmadi", "password", "newPassword");
        mockMvc.perform(put("/api/v1/user/updatePassword")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(TestUtils.convertObjectToJsonBytes(changeLoginRequest)))
                .andExpect(status().isOk())
                .andExpect(content().string("Update password successful"));
    }

}
