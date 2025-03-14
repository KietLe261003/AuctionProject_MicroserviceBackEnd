package com.example.billingandfees_service.repository;

import com.example.billingandfees_service.entity.RegisterAuction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RegisterAuctionRepository extends JpaRepository<RegisterAuction, Long> {
    List<RegisterAuction> findByUserId(Long userId);
    List<RegisterAuction> findByAuctionId(Long auctionId);
    boolean existsByUserIdAndAuctionId(Long userId, Long auctionId);
}
