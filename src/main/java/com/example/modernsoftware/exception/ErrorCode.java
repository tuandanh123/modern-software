package com.example.modernsoftware.exception;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;

@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@AllArgsConstructor
@Getter
public enum ErrorCode {
    UNAUTHENTICATED(1011, "Unauthenticated", HttpStatus.UNAUTHORIZED),
    UNAUTHORIZED(1010, "Unauthorized", HttpStatus.FORBIDDEN),
    JOSEEE_EXCEPTION(1009, "JOSEEE_EXCEPTION", HttpStatus.INTERNAL_SERVER_ERROR),
    INVALID_KEY(1008, "Invalid key", HttpStatus.BAD_REQUEST),
    INVALID_USERNAME(1001,"Invalid username", HttpStatus.BAD_REQUEST),
    INVALID_PASSWORD(1002,"Invalid password", HttpStatus.BAD_REQUEST),
    INVALID_EMAIL(1003,"Invalid email", HttpStatus.BAD_REQUEST),
    INVALID_FIRST_NAME(1004,"Invalid first name", HttpStatus.BAD_REQUEST),
    INVALID_LAST_NAME(1005,"Invalid last name", HttpStatus.BAD_REQUEST),
    UNCATEGORIZED_ERROR(9999, "Uncategorized error", HttpStatus.INTERNAL_SERVER_ERROR),
    USER_EXISTED(1006, "User already exists", HttpStatus.BAD_REQUEST),
    USER_NOT_FOUND(1007, "User not found", HttpStatus.BAD_REQUEST),
    INVALID_DOB(1012, "Your age must be at least {min}", HttpStatus.BAD_REQUEST),
    ;
    int code;
    String message;
    HttpStatusCode statusCode;

}
