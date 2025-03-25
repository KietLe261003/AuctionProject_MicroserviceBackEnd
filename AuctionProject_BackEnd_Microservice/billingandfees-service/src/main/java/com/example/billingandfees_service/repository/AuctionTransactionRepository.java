package com.example.billingandfees_service.repository;

import com.example.billingandfees_service.entity.AuctionTransaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AuctionTransactionRepository extends JpaRepository<AuctionTransaction, Long> {
    List<AuctionTransaction> findByStatus(String status);
    Boolean existsAllByAuctionIdAndUserId(Long auctionId, Long userId);
    AuctionTransaction findByAuctionIdAndUserId(Long auctionId, Long userId);
    List<AuctionTransaction> findByUserId(Long userId);
}
