package com.example.account_service.service;

import com.example.account_service.dto.response.RoleResponse;
import com.example.account_service.entity.Role;
import com.example.account_service.mapper.RoleMapper;
import com.example.account_service.respository.RoleRespository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleService {
    @Autowired
    RoleRespository respository;
    @Autowired
    RoleMapper roleMapper;
    public List<RoleResponse> findAll() {
        List<Role> roles = respository.findAll();
        return roles.stream().map(role -> roleMapper.mapToRoleResponse(role)).toList();
    }
}
