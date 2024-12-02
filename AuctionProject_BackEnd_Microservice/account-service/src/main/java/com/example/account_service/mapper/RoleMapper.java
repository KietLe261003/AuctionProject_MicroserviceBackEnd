package com.example.account_service.mapper;

import com.example.account_service.dto.response.RoleResponse;
import com.example.account_service.entity.Role;
import org.mapstruct.Mapper;

@Mapper
public interface RoleMapper {
    RoleResponse mapToRoleResponse(Role role);
}
