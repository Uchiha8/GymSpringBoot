package com.example.gymspringboot.controller;

import com.example.gymspringboot.GymSpringBootApplication;
import com.example.gymspringboot.domain.TrainingType;
import com.example.gymspringboot.utils.TestUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = GymSpringBootApplication.class)
public class TrainingTypeControllerIT {
    @Autowired
    private TrainingTypeController trainingTypeController;
    @Autowired
    private MockMvc mockMvc;

    @BeforeEach
    void setUp() throws Exception {
        mockMvc = MockMvcBuilders.standaloneSetup(trainingTypeController)
                .build();
    }

    @Test
    public void create() throws Exception {
        mockMvc.perform(post("/api/v1/trainingtype/create")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(TestUtils.convertObjectToJsonBytes(new TrainingType(1L, "newTrainingType"))))
                .andExpect(status().isOk())
                .andExpect(content().string("{\"id\":1,\"name\":\"newTrainingType\"}"));
    }

    @Test
    public void findAll() throws Exception {
        mockMvc.perform(get("/api/v1/trainingtype/findAll")
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andExpect(content().string("[{\"id\":1,\"name\":\"newTrainingType\"}]"));
    }
}
