package com.example.billingandfees_service.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Tax {
    @Id
    Long id;
    String taxName;
    String taxDescription;
    Long taxAmount;
    Boolean delflag;

    @ManyToOne
    @JoinColumn(columnDefinition = "BillItemId",nullable = false,referencedColumnName = "BillItemId")
    @JsonBackReference
    BillItem billItem;
}
