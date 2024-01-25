package com.example.gymspringboot.controller;

import com.example.gymspringboot.dto.request.ActivateProfileRequest;
import com.example.gymspringboot.dto.request.TrainerRegistrationRequest;
import com.example.gymspringboot.dto.request.TrainerTrainingListRequest;
import com.example.gymspringboot.utils.TestUtils;
import com.fasterxml.jackson.core.JsonProcessingException;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@Sql(scripts = "/data.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_CLASS)
@Transactional
@AutoConfigureMockMvc
public class TrainerControllerIT {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private TrainerController trainerController;

    @BeforeEach
    void setUp() throws Exception {
        mockMvc = MockMvcBuilders.standaloneSetup(trainerController)
                .build();
    }

    @Test
    public void profile() throws Exception {
        mockMvc.perform(get("/api/v1/trainer/profile?userName=reza.rezaei")
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(jsonPath("$.firstName").value("reza"))
                .andExpect(jsonPath("$.lastName").value("rezaei"));
    }

    @Test
    public void activeTrainer() throws Exception {
        mockMvc.perform(get("/api/v1/trainer/activeTrainer?username=ahmad.ahmadi")
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].username").value("mehdi.mehdizadeh"))
                .andExpect(jsonPath("$[1].username").value("reza.rezaei"));
    }

    @Test
    public void trainerTrainings() throws Exception {
        TrainerTrainingListRequest request = new TrainerTrainingListRequest("ali.alizadeh", null, null, null);
        mockMvc.perform(get("/api/v1/trainer/trainerTrainings")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(TestUtils.convertObjectToJsonBytes(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].traineeUsername").value("ali.val"))
                .andExpect(jsonPath("$[0].trainingName").value("Sage"));
    }

    @Test
    public void activate() throws Exception {
        ActivateProfileRequest request = new ActivateProfileRequest("ali.alizadeh", true);
        mockMvc.perform(patch("/api/v1/trainer/activate")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(TestUtils.convertObjectToJsonBytes(request)))
                .andExpect(status().isOk())
                .andExpect(content().string("Trainer activated"));
    }
}
