package com.example.modernsoftware.controller;

import com.example.modernsoftware.dto.ApiResponse;
import com.example.modernsoftware.dto.request.AuthenticateRequest;
import com.example.modernsoftware.dto.request.IntrospectRequest;
import com.example.modernsoftware.dto.request.LogoutRequest;
import com.example.modernsoftware.dto.request.RefreshRequest;
import com.example.modernsoftware.dto.response.AuthenticationResponse;
import com.example.modernsoftware.dto.response.IntrospectResponse;
import com.example.modernsoftware.service.AuthenticationService;
import com.nimbusds.jose.JOSEException;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AuthenticationController {
    AuthenticationService authenticationService;

    @PostMapping("/token")
    public ApiResponse<AuthenticationResponse> authenticate(@RequestBody AuthenticateRequest authenticateRequest) {
        AuthenticationResponse authenticationResponse = authenticationService.authenticate(authenticateRequest);

        return ApiResponse.<AuthenticationResponse>builder()
                .result(authenticationResponse)
                .build();
    }

    @PostMapping("/introspect")
    public ApiResponse<IntrospectResponse> introspect(@RequestBody IntrospectRequest introspectRequest)
            throws ParseException, JOSEException {
        IntrospectResponse introspectResponse = authenticationService.introspect(introspectRequest);

        return ApiResponse.<IntrospectResponse>builder()
                .result(introspectResponse)
                .build();
    }

    @PostMapping("/logout")
    public ApiResponse<Void> logout(@RequestBody LogoutRequest logoutRequest) throws ParseException, JOSEException {
        authenticationService.logout(logoutRequest);

        return ApiResponse.<Void>builder()
                .build();
    }

    @PostMapping("/refresh")
    public ApiResponse<AuthenticationResponse> refresh(@RequestBody RefreshRequest refreshRequest)
            throws ParseException, JOSEException {
        AuthenticationResponse authenticationResponse = authenticationService.refreshToken(refreshRequest);

        return ApiResponse.<AuthenticationResponse>builder()
                .result(authenticationResponse)
                .build();
    }
}
