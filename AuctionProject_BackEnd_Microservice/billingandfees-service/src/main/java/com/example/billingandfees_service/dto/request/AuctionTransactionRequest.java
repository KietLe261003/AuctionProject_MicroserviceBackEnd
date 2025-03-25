package com.example.billingandfees_service.dto.request;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
@Getter
@Setter
public class AuctionTransactionRequest {
    private Long auctionId;
    private Long userId;
    private LocalDateTime deadlineDate;
    private Double amount;
}
