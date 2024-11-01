package com.example.modernsoftware.exception;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@AllArgsConstructor
@Getter
public enum ErrorCode {
    INVALID_KEY(1008, "Invalid key"),
    INVALID_USERNAME(1001,"Invalid username"),
    INVALID_PASSWORD(1002,"Invalid password"),
    INVALID_EMAIL(1003,"Invalid email"),
    INVALID_FIRST_NAME(1004,"Invalid first name"),
    INVALID_LAST_NAME(1005,"Invalid last name"),
    UNCATEGORIZED_ERROR(9999, "Uncategorized error"),
    USER_EXISTED(1006, "User already exists"),
    USER_NOT_FOUND(1007, "User not found"),
    ;
    int code;
    String message;

}
