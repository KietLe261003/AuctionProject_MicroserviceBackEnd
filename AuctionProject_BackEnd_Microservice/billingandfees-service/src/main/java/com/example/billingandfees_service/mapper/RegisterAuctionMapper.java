package com.example.billingandfees_service.mapper;

import com.example.billingandfees_service.dto.request.RegisterAuctionRequest;
import com.example.billingandfees_service.entity.RegisterAuction;
import org.mapstruct.Mapper;

@Mapper
public interface RegisterAuctionMapper {
    RegisterAuction toRegisterAuction(RegisterAuctionRequest registerAuctionRequest);
}
