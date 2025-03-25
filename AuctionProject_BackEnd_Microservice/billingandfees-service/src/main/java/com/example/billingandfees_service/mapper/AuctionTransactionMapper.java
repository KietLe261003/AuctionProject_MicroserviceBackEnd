package com.example.billingandfees_service.mapper;

import com.example.billingandfees_service.dto.request.AuctionTransactionRequest;
import com.example.billingandfees_service.dto.request.AuctionTransactionUpdate;
import com.example.billingandfees_service.entity.AuctionTransaction;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper
public interface AuctionTransactionMapper {
    AuctionTransaction toAuctionTransaction(AuctionTransactionRequest auctionTransactionRequest);
    void updateAuctionTransaction(@MappingTarget AuctionTransaction auctionTransaction, AuctionTransactionUpdate auctionTransactionUpdate);
}
