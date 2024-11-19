package com.example.modernsoftware.service;

import java.util.HashSet;
import java.util.List;

import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.modernsoftware.dto.request.UserCreationRequest;
import com.example.modernsoftware.dto.request.UserUpdateRequest;
import com.example.modernsoftware.dto.response.UserResponse;
import com.example.modernsoftware.entity.User;
import com.example.modernsoftware.exception.AppException;
import com.example.modernsoftware.exception.ErrorCode;
import com.example.modernsoftware.mapper.UserMapper;
import com.example.modernsoftware.repository.RoleRepository;
import com.example.modernsoftware.repository.UserRepository;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UserService {
    UserRepository userRepository;
    UserMapper userMapper;
    RoleRepository roleRepository;
    PasswordEncoder passwordEncoder;

    public UserResponse createUser(UserCreationRequest userCreationRequest) {
        if (userRepository.existsByUsername(userCreationRequest.getUsername())) {
            throw new AppException(ErrorCode.USER_EXISTED);
        }

        User user = userMapper.toUser(userCreationRequest);

        String password = passwordEncoder.encode(user.getPassword());
        user.setPassword(password);

        //        HashSet<Role> roles = new HashSet<>();
        //        Role role = new Role();
        //        role.setName("USER");
        //        roles.add(role);
        //        user.setRoles(roles);

        return userMapper.toUserResponse(userRepository.save(user));
    }

    @PostAuthorize("returnObject.username == authentication.name")
    public UserResponse getUser(String id) {
        User user = userRepository.findById(id).orElseThrow(() -> new AppException(ErrorCode.USER_NOT_FOUND));

        return userMapper.toUserResponse(user);
    }

    @PreAuthorize("hasRole('ADMIN')")
    public List<UserResponse> getAllUsers() {
        return userRepository.findAll().stream().map(userMapper::toUserResponse).toList();
    }

    public UserResponse updateUser(String id, UserUpdateRequest userUpdateRequest) {
        User user = userRepository.findById(id).orElseThrow(() -> new AppException(ErrorCode.USER_NOT_FOUND));
        userMapper.updateUser(user, userUpdateRequest);

        String password = passwordEncoder.encode(userUpdateRequest.getPassword());
        user.setPassword(password);

        var roles = roleRepository.findAllById(userUpdateRequest.getRoles());
        user.setRoles(new HashSet<>(roles));

        user = userRepository.save(user);

        return userMapper.toUserResponse(user);
    }

    public UserResponse getMyInfo() {
        var context = SecurityContextHolder.getContext();
        var authentication = context.getAuthentication();
        String username = authentication.getName();

        User user =
                userRepository.findByUsername(username).orElseThrow(() -> new AppException(ErrorCode.USER_NOT_FOUND));

        return userMapper.toUserResponse(user);
    }

    public void deleteUser(String id) {
        userRepository.deleteById(id);
    }
}
