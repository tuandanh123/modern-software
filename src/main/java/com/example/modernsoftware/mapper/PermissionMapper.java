package com.example.modernsoftware.mapper;

import com.example.modernsoftware.dto.request.PermissionRequest;
import com.example.modernsoftware.dto.response.PermissionResponse;
import com.example.modernsoftware.entity.Permission;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PermissionMapper {
    Permission toPermission(PermissionRequest permissionRequest);

    PermissionResponse toPermissionResponse(Permission permission);
}
