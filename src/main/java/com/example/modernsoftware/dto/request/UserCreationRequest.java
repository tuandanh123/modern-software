package com.example.modernsoftware.dto.request;

import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserCreationRequest {
    @Size(min = 3, max = 20, message = "INVALID_USERNAME")
    String username;
    @Size(min = 8, message = "INVALID_PASSWORD")
    String password;
    @Size(min = 2, max = 50, message = "INVALID_FIRSTNAME")
    String firstName;
    @Size(min = 2, max = 50, message = "INVALID_LASTNAME")
    String lastName;
    LocalDate dbo;
}
