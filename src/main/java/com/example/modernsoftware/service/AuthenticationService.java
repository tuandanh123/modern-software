package com.example.modernsoftware.service;

import com.example.modernsoftware.dto.request.AuthenticateRequest;
import com.example.modernsoftware.dto.response.AuthenticationResponse;
import com.example.modernsoftware.exception.AppException;
import com.example.modernsoftware.exception.ErrorCode;
import com.example.modernsoftware.repository.UserRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class AuthenticationService {
    UserRepository userRepository;
    PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(10);

    public AuthenticationResponse authenticate(AuthenticateRequest authenticateRequest) {
        String username = authenticateRequest.getUsername();
        if(!userRepository.existsByUsername(username))
            throw new AppException(ErrorCode.USER_NOT_FOUND);

        boolean authenticated = passwordEncoder.matches(authenticateRequest.getPassword(),
                passwordEncoder.encode(authenticateRequest.getPassword()));

        return AuthenticationResponse.builder()
                .authenticated(authenticated)
                .build();
    }
}
