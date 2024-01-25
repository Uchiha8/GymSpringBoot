package com.example.gymspringboot.controller;

import com.example.gymspringboot.dto.request.ActivateProfileRequest;
import com.example.gymspringboot.dto.request.TraineeTrainingsListRequest;
import com.example.gymspringboot.dto.request.UpdateTraineeRequest;
import com.example.gymspringboot.utils.TestUtils;
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

import java.util.Date;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@Sql(scripts = "/data.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_CLASS)
@Transactional
public class TraineeControllerIT {
    @Autowired
    private TraineeController traineeController;
    @Autowired
    private MockMvc mockMvc;

    @BeforeEach
    void setUp() throws Exception {
        mockMvc = MockMvcBuilders.standaloneSetup(traineeController)
                .build();
    }

//    @Test
//    public void register() throws Exception {
//        Date dateOfBirth = new Date();
//        TraineeRegistrationRequest request = new TraineeRegistrationRequest("Alisher", "Usmonov", dateOfBirth, "33 Mirobod");
//        mockMvc.perform(post("/api/v1/trainee/register")
//                        .contentType(MediaType.APPLICATION_JSON_VALUE)
//                        .content(TestUtils.convertObjectToJsonBytes(request)))
//                .andExpect(status().isCreated())
//                .andExpect(jsonPath("$.username").value("alisher.usmonov"))
//                .andExpect(jsonPath("$.password").isNotEmpty());
//    }

    @Test
    public void profile() throws Exception {
        mockMvc.perform(get("/api/v1/trainee/profile?userName=saeed.saeedi")
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.firstName").value("saeed"))
                .andExpect(jsonPath("$.lastName").value("saeedi"))
                .andExpect(jsonPath("$.address").value("Kadishva"));
    }

    @Test
    public void traineeTrainings() throws Exception {
        TraineeTrainingsListRequest request = new TraineeTrainingsListRequest("ali.val", null, null, "ali.alizadeh", null);
        mockMvc.perform(get("/api/v1/trainee/traineeTrainings")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(TestUtils.convertObjectToJsonBytes(request)))
                .andExpect(status().isOk());
    }

    @Test
    public void update() throws Exception {
        UpdateTraineeRequest request = new UpdateTraineeRequest("ali.val", "ali", "val", new Date(), "Mirobod", false);
        mockMvc.perform(put("/api/v1/trainee/update")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(TestUtils.convertObjectToJsonBytes(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.username").value("ali.val1"))
                .andExpect(jsonPath("$.firstName").value("ali"))
                .andExpect(jsonPath("$.lastName").value("val"))
                .andExpect(jsonPath("$.address").value("Mirobod"))
                .andExpect(jsonPath("$.active").value(false));
    }

    @Test
    public void activate() throws Exception {
        ActivateProfileRequest request = new ActivateProfileRequest("ali.val", false);
        mockMvc.perform(patch("/api/v1/trainee/activate")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(TestUtils.convertObjectToJsonBytes(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").value("Trainee deactivated"));
    }
}
