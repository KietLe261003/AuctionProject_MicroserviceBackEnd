package com.example.billingandfees_service.dto.request;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;
@Getter
@Setter
public class BillRequestUpdate {
    Long userId;
    Long staffId;
    LocalDate paymentTerm;
    Boolean paymentStatus;
}
