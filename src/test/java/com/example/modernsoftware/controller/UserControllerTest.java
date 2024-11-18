package com.example.modernsoftware.controller;

import com.example.modernsoftware.dto.request.UserCreationRequest;
import com.example.modernsoftware.dto.request.UserUpdateRequest;
import com.example.modernsoftware.dto.response.PermissionResponse;
import com.example.modernsoftware.dto.response.RoleResponse;
import com.example.modernsoftware.dto.response.UserResponse;
import com.example.modernsoftware.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@SpringBootTest
@Slf4j
@AutoConfigureMockMvc
@FieldDefaults(level = AccessLevel.PRIVATE)
class UserControllerTest {
    @Autowired
    MockMvc mockMvc;

    @MockBean
    UserService userService;

    final static String USER_ENDPOINT = "/users";
    UserCreationRequest userCreationRequest;
    UserUpdateRequest userUpdateRequest;
    UserResponse userResponse;
    RoleResponse roleResponse;
    PermissionResponse permissionResponse;
    private ObjectMapper objectMapper = new ObjectMapper();

    @BeforeEach
    void initData(){

        objectMapper.registerModule(new JavaTimeModule());
        LocalDate dob = LocalDate.of(2000, 1, 1);

        permissionResponse = PermissionResponse.builder()
                .name("CREATE_AT")
                .description("CREATE_AT")
                .build();

        var permissions = Set.of(permissionResponse);

        roleResponse = RoleResponse.builder()
                .name("ADMIN")
                .description("ADMIN")
                .permissions(permissions)
                .build();

        var roles1 = List.of("ADMIN", "USER");

        var roles2 = Set.of(roleResponse);

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

        userResponse = UserResponse.builder()
                .id("dkadkakda")
                .username("tuandanh")
                .firstName("tuan")
                .lastName("han")
                .dbo(dob)
                .roles(roles2)
                .build();
    }

    @Test
    void createUser_validRequest_success() throws Exception {
        //GIVEN
        String content = objectMapper.writeValueAsString(userCreationRequest);

        Mockito.when(userService.createUser(ArgumentMatchers.any())).thenReturn(userResponse);

        //WHEN,THEN

        mockMvc.perform(MockMvcRequestBuilders
                .post(USER_ENDPOINT)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(content))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("code").value(1000))
                .andExpect(MockMvcResultMatchers.jsonPath("result.id").value("dkadkakda"));

    }

    @Test
    void createUser_usernameInvalid_fail() throws Exception {
        //GIVEN
        userCreationRequest.setUsername("da");
        String content = objectMapper.writeValueAsString(userCreationRequest);

        //WHEN,THEN
        mockMvc.perform(MockMvcRequestBuilders
                .post(USER_ENDPOINT)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(content))
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andExpect(MockMvcResultMatchers.jsonPath("code").value(1001))
                .andExpect(MockMvcResultMatchers.jsonPath("message").value("Invalid username"));


    }

    @Test
    void createUser_passwordInvalid_fail() throws Exception {
        //GIVEN
        userCreationRequest.setPassword("tu");
        String content = objectMapper.writeValueAsString(userCreationRequest);

        //WHEN,THEN
        mockMvc.perform(MockMvcRequestBuilders
                .post(USER_ENDPOINT)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(content))
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andExpect(MockMvcResultMatchers.jsonPath("code").value(1002))
                .andExpect(MockMvcResultMatchers.jsonPath("message").value("Invalid password"));
    }

    @Test
    void createUser_firstnameInvalid_fail() throws Exception {
        //GIVEN
        userCreationRequest.setFirstName("t");
        String content = objectMapper.writeValueAsString(userCreationRequest);

        //WHEN,THEN
        mockMvc.perform(MockMvcRequestBuilders
                .post(USER_ENDPOINT)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(content))
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andExpect(MockMvcResultMatchers.jsonPath("code").value(1004))
                .andExpect(MockMvcResultMatchers.jsonPath("message").value("Invalid first name"));
    }

    @Test
    void createUser_lastnameInvalid_fail() throws Exception {
        //GIVEN
        userCreationRequest.setLastName("t");
        String content = objectMapper.writeValueAsString(userCreationRequest);

        //WHEN,THEN
        mockMvc.perform(MockMvcRequestBuilders
                .post(USER_ENDPOINT)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(content))
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andExpect(MockMvcResultMatchers.jsonPath("code").value(1005))
                .andExpect(MockMvcResultMatchers.jsonPath("message").value("Invalid last name"));
    }

    //STILL USE STATIC MESSAGE, NEED TO CHANGE TO OPTIMIZE
    @Test
    void createUser_dobInvalid_fail() throws Exception {
        //GIVEN
        var curDob = LocalDate.of(2020, 1, 1);
        userCreationRequest.setDbo(curDob);
        String content = objectMapper.writeValueAsString(userCreationRequest);

        //WHEN,THEN

        mockMvc.perform(MockMvcRequestBuilders
                .post(USER_ENDPOINT)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(content))
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andExpect(MockMvcResultMatchers.jsonPath("code").value(1012))
                .andExpect(MockMvcResultMatchers.jsonPath("message")
                        .value("Your age must be at least 18"));
    }

    //---------------------------
//    @Test
//    void updateUser_validRequest_success() throws Exception {
//        //GIVEN
//        String content = objectMapper.writeValueAsString(userUpdateRequest);
//        String mockJwtToken = "Bearer sample-valid-jwt-token";
//
//        Mockito.when(userService.updateUser(ArgumentMatchers.any(), ArgumentMatchers.any())).thenReturn(userResponse);
//
//        // Mock the CustomJwtDecoder to return a valid Jwt object
//        Jwt mockJwt = Jwt.withTokenValue(mockJwtToken)
//                .header("alg", "HS512")
//                .claim("sub", "testUser")
//                .claim("roles", List.of("ROLE_USER"))
//                .build();
//        Mockito.when(customJwtDecoder.decode(ArgumentMatchers.anyString())).thenReturn(mockJwt);
//
//        //WHEN,THEN
//
//        mockMvc.perform(MockMvcRequestBuilders
//                        .put(USER_ENDPOINT)
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
