package com.example.modernsoftware.mapper;

import com.example.modernsoftware.dto.request.RoleRequest;
import com.example.modernsoftware.dto.response.RoleResponse;
import com.example.modernsoftware.entity.Role;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface RoleMapper {
    @Mapping(target = "permissions", ignore = true)
    Role toRole(RoleRequest roleRequest);

    RoleResponse toRoleResponse(Role role);
}