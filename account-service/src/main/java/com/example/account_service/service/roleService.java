package com.example.account_service.service;

import com.example.account_service.entity.Role;
import com.example.account_service.respository.roleRespository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class roleService {
    @Autowired
    roleRespository respository;
    public List<Role> findAll() {
        return respository.findAll();
    }
}
