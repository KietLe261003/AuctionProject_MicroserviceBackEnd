package com.example.billingandfees_service.dto.request;

import com.example.billingandfees_service.entity.Bill;
import com.example.billingandfees_service.entity.Tax;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.CascadeType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class BillItemRequest {
    Long assetId;
    Double price;
    List<Long> listTaxId;
}
