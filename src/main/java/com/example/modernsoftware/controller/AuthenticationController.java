package com.example.modernsoftware.controller;

import com.example.modernsoftware.dto.ApiResponse;
import com.example.modernsoftware.dto.request.AuthenticateRequest;
import com.example.modernsoftware.dto.response.AuthenticationResponse;
import com.example.modernsoftware.service.AuthenticationService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AuthenticationController {
    AuthenticationService authenticationService;

    @PostMapping("/log-in")
    public ApiResponse<AuthenticationResponse> authenticate(AuthenticateRequest authenticateRequest) {
        AuthenticationResponse authenticationResponse = authenticationService.authenticate(authenticateRequest);

        return ApiResponse.<AuthenticationResponse>builder()
                .result(authenticationResponse)
                .build();
    }
}
