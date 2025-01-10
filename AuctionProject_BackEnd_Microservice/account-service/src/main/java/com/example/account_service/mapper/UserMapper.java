package com.example.account_service.mapper;

import com.example.account_service.dto.request.user.UserCreation;
import com.example.account_service.dto.request.user.UserCreationByAdmin;
import com.example.account_service.dto.request.user.UserUpdate;
import com.example.account_service.dto.response.UserResponse;
import com.example.account_service.entity.Role;
import com.example.account_service.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Named;

@Mapper(componentModel = "spring")
public interface UserMapper {

    User toUser(UserCreation userCreation);

    @Mapping(target = "role", source = "role", qualifiedByName = "mapRole")
    User toUser(UserCreationByAdmin userCreationByAdmin);

    void updateUser(@MappingTarget User user, UserUpdate userUpdate);

    @Mapping(source = "role.id", target = "role") // Map role.roleId sang roleId
    UserResponse toUserResponse(User user);

    @Named("mapRole")
    default Role mapRole(Long roleId) {
        if (roleId == null) {
            return null;
        }
        Role role = new Role();
        role.setId(roleId);
        return role;
    }
}
