package com.example.account_service.service;


import com.example.account_service.base_exception.ErrorCode;
import com.example.account_service.base_exception.AppException;
import com.example.account_service.dto.request.user.UserCreation;
import com.example.account_service.dto.request.user.UserUpdate;
import com.example.account_service.entity.Role;
import com.example.account_service.entity.User;
import com.example.account_service.mapper.UserMapper;
import com.example.account_service.respository.RoleRespository;
import com.example.account_service.respository.UserRespository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    UserRespository respository;
    @Autowired
    RoleRespository respositoryRole;
    @Autowired
    UserMapper userMapper;
    public List<User> findAll() {
        return respository.findAll();
    }

    public User findById(Long id) {
        return respository.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.User_NOT_FOUND));
    }
    public User create(UserCreation userCreation) {
        User user = userMapper.toUser(userCreation);
        Role role = respositoryRole.findById(1L).orElseThrow(()->new AppException(ErrorCode.Role_NOT_FOUND));
        user.setRole(role);
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(10);
        user.setPassword(passwordEncoder.encode(userCreation.getPassword()));
        return respository.save(user);
    }
    public User update(Long id, UserUpdate userUpdate) {
        User user = findById(id);
        userMapper.updateUser(user,userUpdate);
        if(userUpdate.getRoleId()!=null)
        {
            Role role = respositoryRole.findById(userUpdate.getRoleId()).orElseThrow(()-> new AppException(ErrorCode.Role_NOT_FOUND));
            user.setRole(role);
        }
        return respository.save(user);
    }
    public void delete(Long id) {
        respository.deleteById(id);
    }
}
