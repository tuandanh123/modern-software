package com.example.modernsoftware.controller;

import com.example.modernsoftware.dto.ApiResponse;
import com.example.modernsoftware.dto.request.RoleRequest;
import com.example.modernsoftware.dto.response.RoleResponse;
import com.example.modernsoftware.service.RoleService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/roles")
@RequiredArgsConstructor
@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class RoleController {
    RoleService roleService;

    @PostMapping
    public ApiResponse<RoleResponse> createRole(@RequestBody RoleRequest roleRequest) {
        RoleResponse roleResponse = roleService.createRole(roleRequest);

        return ApiResponse.<RoleResponse>builder()
                .result(roleResponse)
                .build();
    }

    @GetMapping
    public ApiResponse<List<RoleResponse>> getRoles() {
        List<RoleResponse> roleResponseList = roleService.getAllRoles();

        return ApiResponse.<List<RoleResponse>>builder()
                .result(roleResponseList)
                .build();
    }

    @DeleteMapping("/{id}")
    public ApiResponse<String> deleteRole(@PathVariable String id){
        roleService.deleteRole(id);

        return ApiResponse.<String>builder()
                .result("role deleted")
                .build();
    }
}
