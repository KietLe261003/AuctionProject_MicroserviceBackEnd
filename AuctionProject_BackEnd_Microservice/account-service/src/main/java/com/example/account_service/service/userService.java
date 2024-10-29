package com.example.account_service.service;


import com.example.account_service.entity.User;
import com.example.account_service.respository.userRespository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class userService {
    @Autowired
    userRespository respository;
    public List<User> findAll() {
        return respository.findAll();
    }
}
