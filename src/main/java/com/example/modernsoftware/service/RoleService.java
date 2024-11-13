package com.example.modernsoftware.service;

import com.example.modernsoftware.dto.request.RoleRequest;
import com.example.modernsoftware.dto.response.RoleResponse;
import com.example.modernsoftware.entity.Role;
import com.example.modernsoftware.mapper.RoleMapper;
import com.example.modernsoftware.repository.PermissionRepository;
import com.example.modernsoftware.repository.RoleRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
@Slf4j
public class RoleService {
    RoleRepository roleRepository;
    PermissionRepository permissionRepository;
    RoleMapper roleMapper;

    public RoleResponse createRole(RoleRequest roleRequest) {
        Role role = roleMapper.toRole(roleRequest);

        var permissions = permissionRepository.findAllById(roleRequest.getPermissions());

        role.setPermissions(new HashSet<>(permissions));

        role = roleRepository.save(role);

        return roleMapper.toRoleResponse(role);
    }

    public List<RoleResponse> getAllRoles(){
        return roleRepository.findAll().stream()
                .map(roleMapper::toRoleResponse).toList();
    }

    public void deleteRole(String role){roleRepository.deleteById(role);}
}
