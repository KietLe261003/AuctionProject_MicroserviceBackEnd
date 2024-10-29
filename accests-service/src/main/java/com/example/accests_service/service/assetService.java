package com.example.accests_service.service;

import com.example.accests_service.entity.Asset;
import com.example.accests_service.respository.assetRespository;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class assetService {
    @Autowired
    assetRespository respository;
    public List<Asset> findAll() {
        return respository.findAll();
    }
}
