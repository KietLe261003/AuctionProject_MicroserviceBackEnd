package com.example.billingandfees_service.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class AuctionTransaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long auctionId;
    private Long userId;
    private LocalDateTime deadlineDate;
    private LocalDateTime submitDate;
    private Double amount;
    private String status;
    private LocalDateTime createAt;
    private LocalDateTime updateAt;
}
