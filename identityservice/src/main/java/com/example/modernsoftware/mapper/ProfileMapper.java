package com.example.modernsoftware.mapper;

import com.example.modernsoftware.dto.request.ProfileCreationRequest;
import com.example.modernsoftware.dto.request.UserCreationRequest;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProfileMapper {
    ProfileCreationRequest toProfileCreationRequest(UserCreationRequest request);
}
