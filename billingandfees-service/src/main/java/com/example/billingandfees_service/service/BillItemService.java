package com.example.billingandfees_service.service;

import com.example.billingandfees_service.base_exception.AppException;
import com.example.billingandfees_service.base_exception.ErrorCode;
import com.example.billingandfees_service.dto.request.BillItemRequest;
import com.example.billingandfees_service.entity.Bill;
import com.example.billingandfees_service.entity.BillItem;
import com.example.billingandfees_service.entity.Tax;
import com.example.billingandfees_service.mapper.BillItemMapper;
import com.example.billingandfees_service.repository.BillItemRepository;
import com.example.billingandfees_service.repository.BillRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BillItemService {
    @Autowired
    private BillItemRepository billItemRepository;
    @Autowired
    private BillRepository billRepository;
    @Autowired
    private BillService billService;
    @Autowired
    private BillItemMapper billItemMapper;
    public List<BillItem> getAllBillItemsByBillId(Long billId) {
        List<BillItem> billItemList=billItemRepository.findAll();
        List<BillItem> res=new ArrayList<>();
        for (BillItem billItem : billItemList) {
            if(billItem.getBill().getId().equals(billId)){
                res.add(billItem);
            }
        }
        return res;
    }
    public Bill createBillItem(Long billId, BillItemRequest billItemRequest) {
        Bill bill=billRepository.findById(billId).orElseThrow(()->new AppException(ErrorCode.NOT_FOUND_BILL));
        BillItem billItem = billItemMapper.toBillItem(billItemRequest);
        List<Tax> taxes = billService.listTaxConvert(billItemRequest.getListTaxId());
        billItem.setTaxes(taxes);
        billItem.setTaxAmount(billService.totalTaxPrice(billItemRequest.getPrice(),taxes));
        billItem.setTotalAmount(billService.totalTaxPrice(billItemRequest.getPrice(),taxes)+billItemRequest.getPrice());

        bill.setTotalAmount(bill.getTotalAmount()+billItem.getTotalAmount());
        billItem.setBill(bill);
        billRepository.save(bill);
        billItemRepository.save(billItem);
        return bill;
    }
    public Bill updateBillItem(Long billId,Long billItemId, BillItemRequest billItemRequest) {
        Bill bill=billRepository.findById(billId).orElseThrow(()->new AppException(ErrorCode.NOT_FOUND_BILL));
        BillItem billItem = billItemRepository.findById(billItemId).orElseThrow(()->new AppException(ErrorCode.NOT_FOUND_BILLITEM));
        bill.setTotalAmount(bill.getTotalAmount()-billItem.getTotalAmount());
        billItemMapper.updateBillItem(billItemRequest,billItem);
        List<Tax> taxes = billService.listTaxConvert(billItemRequest.getListTaxId());
        billItem.setTaxes(taxes);
        billItem.setTaxAmount(billService.totalTaxPrice(billItemRequest.getPrice(),taxes));
        billItem.setTotalAmount(billService.totalTaxPrice(billItemRequest.getPrice(),taxes)+billItemRequest.getPrice());
        bill.setTotalAmount(bill.getTotalAmount()+billItem.getTotalAmount());
        billItem.setBill(bill);
        billRepository.save(bill);
        billItemRepository.save(billItem);
        return bill;
    }
    public Bill deleteBillItem(Long billId,Long billItemId) {
        Bill bill=billRepository.findById(billId).orElseThrow(()->new AppException(ErrorCode.NOT_FOUND_BILL));
        BillItem billItem = billItemRepository.findById(billItemId).orElseThrow(()->new AppException(ErrorCode.NOT_FOUND_BILLITEM));
        bill.setTotalAmount(bill.getTotalAmount()-billItem.getTotalAmount());
        billItemRepository.delete(billItem);
        billRepository.save(bill);
        return bill;
    }

}
