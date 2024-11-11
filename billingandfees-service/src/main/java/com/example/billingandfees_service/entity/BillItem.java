package com.example.billingandfees_service.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.util.List;

@Entity
public class BillItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "BillItemId")
    Long id;
    Long assetId;
    Long price;
    Long taxAmount;
    Long totalAmount;
    @ManyToOne
    @JoinColumn(columnDefinition = "BillId",nullable = false,referencedColumnName = "BillId")
    @JsonBackReference
    Bill bill;

    @OneToMany(mappedBy = "billItem", cascade = CascadeType.ALL)
    @JsonManagedReference
    List<Tax> taxs;
}
