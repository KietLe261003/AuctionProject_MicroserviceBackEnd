package com.example.billingandfees_service.service;

import com.example.billingandfees_service.base_exception.AppException;
import com.example.billingandfees_service.base_exception.ErrorCode;
import com.example.billingandfees_service.entity.Bill;
import com.example.billingandfees_service.repository.BillRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BillService {
    @Autowired
    private BillRepository billRepository;
    public List<Bill> getAllBills() {
        return billRepository.findAll();
    }
    public Bill getBillById(Long id) {
        return billRepository.findById(id).orElseThrow(()->new AppException(ErrorCode.NOT_FOUND_BILL));
    }
}
