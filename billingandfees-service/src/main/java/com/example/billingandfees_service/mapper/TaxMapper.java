package com.example.billingandfees_service.mapper;

import com.example.billingandfees_service.dto.request.TaxRequest;
import com.example.billingandfees_service.entity.Tax;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.TargetType;

@Mapper
public interface TaxMapper {
    Tax toTax(TaxRequest taxRequest);
    void updateTax(@MappingTarget Tax tax, TaxRequest taxRequest);
}
