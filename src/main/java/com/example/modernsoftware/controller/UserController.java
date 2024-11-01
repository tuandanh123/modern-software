package com.example.modernsoftware.controller;

import com.example.modernsoftware.dto.ApiResponse;
import com.example.modernsoftware.dto.request.UserCreationRequest;
import com.example.modernsoftware.dto.request.UserUpdateRequest;
import com.example.modernsoftware.dto.response.UserResponse;
import com.example.modernsoftware.service.UserService;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UserController {
    UserService userService;

    @PostMapping
    public ApiResponse<UserResponse> createUser(@RequestBody @Valid UserCreationRequest userCreationRequest) {
        UserResponse userResponse = userService.createUser(userCreationRequest);

        return ApiResponse.<UserResponse>builder()
                .result(userResponse)
                .build();
    }

    @GetMapping
    public ApiResponse<List<UserResponse>> getUsers() {
        List<UserResponse> userResponseList = userService.getAllUsers();

        return ApiResponse.<List<UserResponse>>builder()
                .result(userResponseList)
                .build();
    }

    @GetMapping("/{userId}")
    public ApiResponse<UserResponse> getUser(@PathVariable String userId) {
        UserResponse userResponse = userService.getUser(userId);

        return ApiResponse.<UserResponse>builder()
                .result(userResponse)
                .build();
    }

    @PutMapping("/{userId}")
    public ApiResponse<UserResponse> updateUser(@PathVariable String userId,
                                                @RequestBody @Valid UserUpdateRequest userUpdateRequest) {
        UserResponse userResponse = userService.updateUser(userId, userUpdateRequest);

        return ApiResponse.<UserResponse>builder()
                .result(userResponse)
                .build();
    }

    @DeleteMapping("/{userId}")
    public ApiResponse<String> deleteUser(@PathVariable String userId) {
        userService.deleteUser(userId);

        return ApiResponse.<String>builder()
                .result("User deleted")
                .build();
    }
}
