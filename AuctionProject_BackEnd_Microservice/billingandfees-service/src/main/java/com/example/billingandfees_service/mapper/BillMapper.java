package com.example.billingandfees_service.mapper;

import com.example.billingandfees_service.dto.request.BillRequestUpdate;
import com.example.billingandfees_service.entity.Bill;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper
public interface BillMapper {
    void updateBill(@MappingTarget Bill bill, BillRequestUpdate billRequestUpdate);
}
