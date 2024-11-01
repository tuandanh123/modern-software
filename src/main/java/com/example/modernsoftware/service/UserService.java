package com.example.modernsoftware.service;

import com.example.modernsoftware.dto.request.UserCreationRequest;
import com.example.modernsoftware.dto.request.UserUpdateRequest;
import com.example.modernsoftware.dto.response.UserResponse;
import com.example.modernsoftware.entity.User;
import com.example.modernsoftware.exception.AppException;
import com.example.modernsoftware.exception.ErrorCode;
import com.example.modernsoftware.mapper.UserMapper;
import com.example.modernsoftware.repository.UserRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UserService {
    UserRepository userRepository;
    UserMapper userMapper;

    public UserResponse createUser(UserCreationRequest userCreationRequest){
        if(userRepository.existsById(userCreationRequest.getUsername())){
            throw new AppException(ErrorCode.USER_EXISTED);
        }

        User user = userRepository.save(userMapper.toUser(userCreationRequest));

        return userMapper.toUserResponse(user);
    }

    public UserResponse getUser(String id){
        User user = userRepository.findById(id).orElseThrow(
                () -> new AppException(ErrorCode.USER_NOT_FOUND)
        );

        return userMapper.toUserResponse(user);
    }

    public List<UserResponse> getAllUsers(){
        return userRepository.findAll().stream()
                .map(userMapper::toUserResponse).toList();
    }

    public UserResponse updateUser(String id, UserUpdateRequest userUpdateRequest){
        User user = userRepository.findById(id).orElseThrow(
                () -> new AppException(ErrorCode.USER_NOT_FOUND)
        );

        userMapper.updateUser(user, userUpdateRequest);

        return userMapper.toUserResponse(user);
    }

    public void deleteUser(String id){userRepository.deleteById(id);}
}
