package com.example.billingandfees_service.dto.request;

import com.example.billingandfees_service.entity.TaxType;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TaxRequest {
    String taxName;
    String taxDescription;
    Double taxAmount;
    Boolean delflag;
    TaxType taxType;
}
