package com.example.modernsoftware.controller;

import java.util.List;

import org.springframework.web.bind.annotation.*;

import com.example.modernsoftware.dto.ApiResponse;
import com.example.modernsoftware.dto.request.PermissionRequest;
import com.example.modernsoftware.dto.response.PermissionResponse;
import com.example.modernsoftware.service.PermissionService;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/permissions")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class PermissionController {
    PermissionService permissionService;

    @PostMapping
    public ApiResponse<PermissionResponse> createPermission(@RequestBody PermissionRequest permissionRequest) {
        PermissionResponse permissionResponse = permissionService.createPermission(permissionRequest);

        return ApiResponse.<PermissionResponse>builder()
                .result(permissionResponse)
                .build();
    }

    @GetMapping
    public ApiResponse<List<PermissionResponse>> getPermissions() {
        List<PermissionResponse> permissionResponseList = permissionService.getAllPermissions();

        return ApiResponse.<List<PermissionResponse>>builder()
                .result(permissionResponseList)
                .build();
    }

    @DeleteMapping("/{id}")
    public ApiResponse<String> deletePermission(@PathVariable String id) {
        permissionService.deletePermission(id);

        return ApiResponse.<String>builder().result("permission deleted").build();
    }
}
