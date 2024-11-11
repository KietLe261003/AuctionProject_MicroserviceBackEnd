package com.example.billingandfees_service.service;

import com.example.billingandfees_service.base_exception.AppException;
import com.example.billingandfees_service.base_exception.ErrorCode;
import com.example.billingandfees_service.dto.request.BillItemRequest;
import com.example.billingandfees_service.dto.request.BillRequest;
import com.example.billingandfees_service.entity.Bill;
import com.example.billingandfees_service.entity.BillItem;
import com.example.billingandfees_service.entity.Tax;
import com.example.billingandfees_service.entity.TaxType;
import com.example.billingandfees_service.mapper.BillItemMapper;
import com.example.billingandfees_service.repository.BillItemRepository;
import com.example.billingandfees_service.repository.BillRepository;
import com.example.billingandfees_service.repository.TaxRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class BillService {
    @Autowired
    private BillRepository billRepository;
    @Autowired
    private TaxRepository taxRepository;
    @Autowired
    BillItemMapper billItemMapper;
    public List<Bill> getAllBills() {
        return billRepository.findAll();
    }
    public Bill getBillById(Long id) {
        return billRepository.findById(id).orElseThrow(()->new AppException(ErrorCode.NOT_FOUND_BILL));
    }
    public Bill createBill(BillRequest billRequest) {
        Bill bill = new Bill();
        bill.setUserId(billRequest.getUserId());
        bill.setStaffId(billRequest.getStaffId());
        bill.setBillDate(LocalDate.now());
        bill.setPaymentTerm(billRequest.getPaymentTerm());
        bill.setPaymentStatus(false);
        Double totalAmount = 0.0;
        // Lấy danh sách các sản phẩm của bill
        List<BillItem> billItems = new ArrayList<>();
        for (BillItemRequest billItemRequest : billRequest.getBillItems()) {
            BillItem billItem = billItemMapper.toBillItem(billItemRequest);
            List<Tax> taxes = listTaxConvert(billItemRequest.getListTaxId());
            billItem.setTaxes(taxes); //Danh sách các loại thue
            billItem.setTaxAmount(totalTaxPrice(billItemRequest.getPrice(),taxes)); //Tổng tiền thuế
            billItem.setTotalAmount(totalTaxPrice(billItemRequest.getPrice(),taxes)+billItemRequest.getPrice());//Tổng tiền
            billItem.setBill(bill);
            totalAmount += billItem.getTotalAmount();
            billItems.add(billItem);
        }
        bill.setTotalAmount(totalAmount);
        bill.setBillItems(billItems);
        return billRepository.save(bill);
    }
    public List<Tax> listTaxConvert(List<Long> taxIds){
        List<Tax> taxList = taxRepository.findAllById(taxIds);
        return taxList;
    }
    public Double totalTaxPrice(Double priceProduct,List<Tax> taxList){
        Double totalTaxPrice = 0.0;
        for(Tax tax : taxList){
            if(tax.getTaxType().equals(TaxType.Fixed))
                totalTaxPrice+=tax.getTaxAmount();
            else
                totalTaxPrice+=(priceProduct/100)*tax.getTaxAmount();
        }
        return totalTaxPrice;
    }
}
