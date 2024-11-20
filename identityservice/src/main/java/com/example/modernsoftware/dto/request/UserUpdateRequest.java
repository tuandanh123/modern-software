package com.example.modernsoftware.dto.request;

import java.time.LocalDate;
import java.util.List;

import jakarta.validation.constraints.Size;

import com.example.modernsoftware.validator.DobConstraint;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserUpdateRequest {
    @Size(min = 8, message = "INVALID_PASSWORD")
    String password;

    @Size(min = 2, max = 50, message = "INVALID_FIRSTNAME")
    String firstName;

    @Size(min = 2, max = 50, message = "INVALID_LASTNAME")
    String lastName;

    @DobConstraint(min = 18, message = "INVALID_DOB")
    LocalDate dbo;

    List<String> roles;

    String city;
}