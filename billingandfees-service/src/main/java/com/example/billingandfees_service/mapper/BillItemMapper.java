package com.example.billingandfees_service.mapper;

import com.example.billingandfees_service.dto.request.BillItemRequest;
import com.example.billingandfees_service.entity.BillItem;
import org.mapstruct.Mapper;

@Mapper
public interface BillItemMapper {
    BillItem toBillItem(BillItemRequest billItemRequest);
}
