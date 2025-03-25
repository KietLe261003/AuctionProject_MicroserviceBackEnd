package com.example.account_service.service;

import com.example.account_service.dto.request.user.UserCreation;
import com.example.account_service.dto.response.RoleResponse;
import com.example.account_service.entity.Role;
import com.example.account_service.entity.User;
import com.example.account_service.mapper.RoleMapper;
import com.example.account_service.mapper.UserMapper;
import com.example.account_service.respository.RoleRespository;
import com.example.account_service.respository.UserRespository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleService {
    @Autowired
    RoleRespository respository;
    @Autowired
    UserRespository userRespository;
    @Autowired
    UserMapper userMapper;
    @Autowired
    RoleMapper roleMapper;
    public List<RoleResponse> findAll() {
        List<Role> roles = respository.findAll();
        return roles.stream().map(role -> roleMapper.mapToRoleResponse(role)).toList();
    }

    @PostConstruct
    public void initRoles() {
        if (!respository.existsByName("admin")) {
            Role adminRole = new Role();
            adminRole.setName("ADMIN");
            adminRole.setDelflag(false);
            respository.save(adminRole);
            System.out.println("Created role: ADMIN");
        }

        if (!respository.existsByName("user")) {
            Role userRole = new Role();
            userRole.setName("USER");
            userRole.setDelflag(false);
            respository.save(userRole);
            System.out.println("Created role: USER");
        }

        if (!respository.existsByName("inspector")) {
            Role moderatorRole = new Role();
            moderatorRole.setName("INSPECTOR");
            moderatorRole.setDelflag(false);
            respository.save(moderatorRole);
            System.out.println("Created role: INSPECTOR");
        }
        System.out.println("Created role successfully");
        if(!userRespository.findByEmail("admin@gmail.com").isPresent())
        {
            UserCreation userCreation = new UserCreation("admin","123456","admin@gmail.com");
            User admin = userMapper.toUser(userCreation);
            Role adminRole = respository.findByName("admin");
            admin.setRole(adminRole);
            userRespository.save(admin);
            System.out.println("Init admin successfully");
        }
        System.out.println("Created admin successfully");
    }
}
