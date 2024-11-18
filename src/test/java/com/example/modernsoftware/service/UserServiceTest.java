package com.example.modernsoftware.service;

import com.example.modernsoftware.dto.request.UserCreationRequest;
import com.example.modernsoftware.dto.response.PermissionResponse;
import com.example.modernsoftware.dto.response.RoleResponse;
import com.example.modernsoftware.dto.response.UserResponse;
import com.example.modernsoftware.entity.Permission;
import com.example.modernsoftware.entity.Role;
import com.example.modernsoftware.entity.User;
import com.example.modernsoftware.repository.UserRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@SpringBootTest
@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserServiceTest {
    @Autowired
    UserService userService;

    @MockBean
    UserRepository userRepository;

    UserCreationRequest userCreationRequest;
    UserResponse userResponse;
    User user;
    private ObjectMapper objectMapper = new ObjectMapper();

    @BeforeEach
    void initData(){
        objectMapper.registerModule(new JavaTimeModule());
        LocalDate dob = LocalDate.of(2020, 1, 1);

        userCreationRequest = UserCreationRequest.builder()
                .username("john")
                .firstName("John")
                .lastName("Doe")
                .password("12345678")
                .dbo(dob)
                .build();

        userResponse = UserResponse.builder()
                .id("cf0600f538b3")
                .username("john")
                .firstName("John")
                .lastName("Doe")
                .dbo(dob)
                .build();

        user = User.builder()
                .id("cf0600f538b3")
                .username("john")
                .firstName("John")
                .lastName("Doe")
                .dbo(dob)
                .build();
    }

    @Test
    void createUser_validRequest_success(){
        //GIVEN
        Mockito.when(userRepository.existsByUsername(ArgumentMatchers.anyString())).thenReturn(false);
        Mockito.when(userRepository.save(ArgumentMatchers.any())).thenReturn(user);

        //WHEN
        var response = userService.createUser(userCreationRequest);

        //THEN
        Assertions.assertThat(response.getId()).isEqualTo("cf0600f538b3");
        Assertions.assertThat(response.getUsername()).isEqualTo("john");
    }

}
