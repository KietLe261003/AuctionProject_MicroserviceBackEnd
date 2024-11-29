package com.example.billingandfees_service.service;

import com.example.billingandfees_service.base_exception.AppException;
import com.example.billingandfees_service.base_exception.ErrorCode;
import com.example.billingandfees_service.dto.request.TaxRequest;
import com.example.billingandfees_service.entity.Tax;
import com.example.billingandfees_service.mapper.TaxMapper;
import com.example.billingandfees_service.repository.TaxRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaxService {
    @Autowired
    private TaxRepository taxRepository;
    @Autowired
    private TaxMapper taxMapper;
    public List<Tax> getAllTaxes() {
        return taxRepository.findAll();
    }
    public Tax getTaxById(Long id) {
        return taxRepository.findById(id).orElseThrow(()-> new AppException(ErrorCode.NOT_FOUND_TAX));
    }
    public Tax createTax(TaxRequest taxRequest) {
        Tax tax = new Tax();
        tax=taxMapper.toTax(taxRequest);
        return taxRepository.save(tax);
    }
    public Tax updateTax(Long id, TaxRequest taxRequest) {
        Tax tax = getTaxById(id);
        taxMapper.updateTax(tax,taxRequest);
        return taxRepository.save(tax);
    }
    public void deleteTax(Long id) {
        taxRepository.deleteById(id);
    }
}
