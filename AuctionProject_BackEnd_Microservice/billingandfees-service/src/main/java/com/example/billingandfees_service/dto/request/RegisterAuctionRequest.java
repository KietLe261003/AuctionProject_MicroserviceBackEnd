package com.example.billingandfees_service.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegisterAuctionRequest {
    Long userId;
    Long auctionId;
    Double price;
}
