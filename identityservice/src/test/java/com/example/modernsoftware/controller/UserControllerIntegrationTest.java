package com.example.modernsoftware.controller;

import java.time.LocalDate;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import com.example.modernsoftware.dto.request.UserCreationRequest;
import com.example.modernsoftware.dto.request.UserUpdateRequest;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;

@SpringBootTest
@Slf4j
@AutoConfigureMockMvc
@FieldDefaults(level = AccessLevel.PRIVATE)
@Testcontainers
class UserControllerIntegrationTest {
    @Container
    static final MySQLContainer<?> MYSQL_CONTAINER = new MySQLContainer<>("mysql:latest");

    @DynamicPropertySource
    static void configureDataSource(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", MYSQL_CONTAINER::getJdbcUrl);
        registry.add("spring.datasource.username", MYSQL_CONTAINER::getUsername);
        registry.add("spring.datasource.password", MYSQL_CONTAINER::getPassword);
        registry.add("spring.jpa.hibernate.ddl-auto", () -> "update");
        registry.add("spring.datasource.driverClassName", () -> "com.mysql.cj.jdbc.Driver");
    }

    @Autowired
    MockMvc mockMvc;

    static final String USER_ENDPOINT = "/users";
    UserCreationRequest userCreationRequest;
    UserUpdateRequest userUpdateRequest;
    private ObjectMapper objectMapper = new ObjectMapper();

    @BeforeEach
    void initData() {

        objectMapper.registerModule(new JavaTimeModule());
        LocalDate dob = LocalDate.of(2000, 1, 1);

        var roles1 = List.of("ADMIN", "USER");

        userCreationRequest = UserCreationRequest.builder()
                .username("tuandanh")
                .password("tuandanh")
                .firstName("tuan")
                .lastName("han")
                .dbo(dob)
                .build();

        userUpdateRequest = UserUpdateRequest.builder()
                .password("tuandanh")
                .firstName("tuan")
                .lastName("han")
                .dbo(dob)
                .roles(roles1)
                .build();
    }

    @Test
    void createUser_validRequest_success() throws Exception {
        // GIVEN
        String content = objectMapper.writeValueAsString(userCreationRequest);

        // WHEN,THEN

        mockMvc.perform(MockMvcRequestBuilders.post(USER_ENDPOINT)
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(content))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("code").value(1000))
                .andExpect(MockMvcResultMatchers.jsonPath("result.username").value("tuandanh"));
    }

    @Test
    void createUser_usernameInvalid_fail() throws Exception {
        // GIVEN
        userCreationRequest.setUsername("da");
        String content = objectMapper.writeValueAsString(userCreationRequest);

        // WHEN,THEN
        mockMvc.perform(MockMvcRequestBuilders.post(USER_ENDPOINT)
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(content))
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andExpect(MockMvcResultMatchers.jsonPath("code").value(1001))
                .andExpect(MockMvcResultMatchers.jsonPath("message").value("Invalid username"));
    }

    @Test
    void createUser_passwordInvalid_fail() throws Exception {
        // GIVEN
        userCreationRequest.setPassword("tu");
        String content = objectMapper.writeValueAsString(userCreationRequest);

        // WHEN,THEN
        mockMvc.perform(MockMvcRequestBuilders.post(USER_ENDPOINT)
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(content))
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andExpect(MockMvcResultMatchers.jsonPath("code").value(1002))
                .andExpect(MockMvcResultMatchers.jsonPath("message").value("Invalid password"));
    }

    @Test
    void createUser_firstnameInvalid_fail() throws Exception {
        // GIVEN
        userCreationRequest.setFirstName("t");
        String content = objectMapper.writeValueAsString(userCreationRequest);

        // WHEN,THEN
        mockMvc.perform(MockMvcRequestBuilders.post(USER_ENDPOINT)
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(content))
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andExpect(MockMvcResultMatchers.jsonPath("code").value(1004))
                .andExpect(MockMvcResultMatchers.jsonPath("message").value("Invalid first name"));
    }

    @Test
    void createUser_lastnameInvalid_fail() throws Exception {
        // GIVEN
        userCreationRequest.setLastName("t");
        String content = objectMapper.writeValueAsString(userCreationRequest);

        // WHEN,THEN
        mockMvc.perform(MockMvcRequestBuilders.post(USER_ENDPOINT)
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(content))
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andExpect(MockMvcResultMatchers.jsonPath("code").value(1005))
                .andExpect(MockMvcResultMatchers.jsonPath("message").value("Invalid last name"));
    }

    // STILL USE STATIC MESSAGE, NEED TO CHANGE TO OPTIMIZE
    @Test
    void createUser_dobInvalid_fail() throws Exception {
        // GIVEN
        var curDob = LocalDate.of(2020, 1, 1);
        userCreationRequest.setDbo(curDob);
        String content = objectMapper.writeValueAsString(userCreationRequest);

        // WHEN,THEN

        mockMvc.perform(MockMvcRequestBuilders.post(USER_ENDPOINT)
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(content))
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andExpect(MockMvcResultMatchers.jsonPath("code").value(1012))
                .andExpect(MockMvcResultMatchers.jsonPath("message").value("Your age must be at least 18"));
    }

    // ---------------------------
    //    @Test
    //    @WithMockUser(username = "tuandanh")
    //    void updateUser_validRequest_success() throws Exception {
    //        //GIVEN
    //        String content = objectMapper.writeValueAsString(userUpdateRequest);
    //
    //
    //        //WHEN,THEN
    //
    //        mockMvc.perform(MockMvcRequestBuilders
    //                        .put("/users/" + userResponse.getId(), userResponse.getId())
    //                        .contentType(MediaType.APPLICATION_JSON_VALUE)
    //                        .content(content))
    //                .andExpect(MockMvcResultMatchers.status().isOk())
    //                .andExpect(MockMvcResultMatchers.jsonPath("code").value(1000))
    //                .andExpect(MockMvcResultMatchers.jsonPath("result.id").value("dkadkakda"));
    //
    //    }
    //
    //    @Test
    //    void updateUser_passwordInvalid_fail() throws Exception {
    //        //GIVEN
    //        userUpdateRequest.setPassword("tu");
    //        String content = objectMapper.writeValueAsString(userUpdateRequest);
    //
    //        //WHEN,THEN
    //        mockMvc.perform(MockMvcRequestBuilders
    //                        .put(USER_ENDPOINT)
    //                        .contentType(MediaType.APPLICATION_JSON_VALUE)
    //                        .content(content))
    //                .andExpect(MockMvcResultMatchers.status().isBadRequest())
    //                .andExpect(MockMvcResultMatchers.jsonPath("code").value(1002))
    //                .andExpect(MockMvcResultMatchers.jsonPath("message").value("Invalid password"));
    //    }
    //
    //    @Test
    //    void updateUser_firstnameInvalid_fail() throws Exception {
    //        //GIVEN
    //        userUpdateRequest.setFirstName("t");
    //        String content = objectMapper.writeValueAsString(userUpdateRequest);
    //
    //        //WHEN,THEN
    //        mockMvc.perform(MockMvcRequestBuilders
    //                        .put(USER_ENDPOINT)
    //                        .contentType(MediaType.APPLICATION_JSON_VALUE)
    //                        .content(content))
    //                .andExpect(MockMvcResultMatchers.status().isBadRequest())
    //                .andExpect(MockMvcResultMatchers.jsonPath("code").value(1004))
    //                .andExpect(MockMvcResultMatchers.jsonPath("message").value("Invalid first name"));
    //    }
    //
    //    @Test
    //    void updateUser_lastnameInvalid_fail() throws Exception {
    //        //GIVEN
    //        userUpdateRequest.setLastName("t");
    //        String content = objectMapper.writeValueAsString(userUpdateRequest);
    //
    //        //WHEN,THEN
    //        mockMvc.perform(MockMvcRequestBuilders
    //                        .put(USER_ENDPOINT)
    //                        .contentType(MediaType.APPLICATION_JSON_VALUE)
    //                        .content(content))
    //                .andExpect(MockMvcResultMatchers.status().isBadRequest())
    //                .andExpect(MockMvcResultMatchers.jsonPath("code").value(1005))
    //                .andExpect(MockMvcResultMatchers.jsonPath("message").value("Invalid last name"));
    //    }
    //
    //    //STILL USE STATIC MESSAGE, NEED TO CHANGE TO OPTIMIZE
    //    @Test
    //    void updateUser_dobInvalid_fail() throws Exception {
    //        //GIVEN
    //        var curDob = LocalDate.of(2020, 1, 1);
    //        userUpdateRequest.setDbo(curDob);
    //        String content = objectMapper.writeValueAsString(userUpdateRequest);
    //
    //        //WHEN,THEN
    //
    //        mockMvc.perform(MockMvcRequestBuilders
    //                        .put(USER_ENDPOINT)
    //                        .contentType(MediaType.APPLICATION_JSON_VALUE)
    //                        .content(content))
    //                .andExpect(MockMvcResultMatchers.status().isBadRequest())
    //                .andExpect(MockMvcResultMatchers.jsonPath("code").value(1012))
    //                .andExpect(MockMvcResultMatchers.jsonPath("message")
    //                        .value("Your age must be at least 18"));
    //    }

}
