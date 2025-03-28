package com.example.account_service.mapper;

import com.example.account_service.dto.request.user.UserCreation;
import com.example.account_service.dto.request.user.UserUpdate;
import com.example.account_service.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface UserMapper {
    User toUser(UserCreation userCreation);
    void updateUser(@MappingTarget User user, UserUpdate userUpdate);
}
