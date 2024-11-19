package com.example.modernsoftware.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.modernsoftware.dto.request.PermissionRequest;
import com.example.modernsoftware.dto.response.PermissionResponse;
import com.example.modernsoftware.entity.Permission;
import com.example.modernsoftware.mapper.PermissionMapper;
import com.example.modernsoftware.repository.PermissionRepository;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class PermissionService {
    PermissionRepository permissionRepository;
    PermissionMapper permissionMapper;

    public PermissionResponse createPermission(PermissionRequest permissionRequest) {
        Permission permission = permissionMapper.toPermission(permissionRequest);

        permission = permissionRepository.save(permission);

        return permissionMapper.toPermissionResponse(permission);
    }

    public List<PermissionResponse> getAllPermissions() {
        return permissionRepository.findAll().stream()
                .map(permissionMapper::toPermissionResponse)
                .toList();
    }

    public void deletePermission(String permission) {
        permissionRepository.deleteById(permission);
    }
}
