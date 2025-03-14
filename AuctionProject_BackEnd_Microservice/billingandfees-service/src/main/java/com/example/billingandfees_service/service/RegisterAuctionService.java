package com.example.billingandfees_service.service;

import com.example.billingandfees_service.dto.request.RegisterAuctionRequest;
import com.example.billingandfees_service.entity.RegisterAuction;
import com.example.billingandfees_service.mapper.RegisterAuctionMapper;
import com.example.billingandfees_service.repository.RegisterAuctionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RegisterAuctionService {
    @Autowired
    RegisterAuctionRepository registerAuctionRepository;

    @Autowired
    RegisterAuctionMapper registerAuctionMapper;

    public List<RegisterAuction> findAll() {
        return registerAuctionRepository.findAll();
    }
    public RegisterAuction findById(Long id) {
        return registerAuctionRepository.findById(id).get();
    }
    public RegisterAuction save(RegisterAuctionRequest registerAuctionRequest) {
        RegisterAuction newRegisterAuction = new RegisterAuction();
        newRegisterAuction.setAuctionId(registerAuctionRequest.getAuctionId());
        newRegisterAuction.setUserId(registerAuctionRequest.getUserId());
        newRegisterAuction.setPrice(registerAuctionRequest.getPrice());
        newRegisterAuction.setStatus(true);
        return registerAuctionRepository.save(newRegisterAuction);
    }
    public List<RegisterAuction> findByUserId(Long userId) {
        List<RegisterAuction> registerAuctionList = registerAuctionRepository.findByUserId(userId);
        return registerAuctionList;
    }
    public List<RegisterAuction> findByAuctionId(Long auctionId) {
        List<RegisterAuction> registerAuctionList = registerAuctionRepository.findByAuctionId(auctionId);
        return registerAuctionList;
    }
    public Boolean exitsByUserIdAndByAuctionId(Long userId, Long auctionId) {
        Boolean check = registerAuctionRepository.existsByUserIdAndAuctionId(userId, auctionId);
        return check;
    }
    public void delete(Long id) {
        RegisterAuction registerAuction = registerAuctionRepository.findById(id).get();
        registerAuctionRepository.delete(registerAuction);
    }

}
