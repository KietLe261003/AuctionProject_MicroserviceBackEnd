package com.example.billingandfees_service.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class BillItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "BillItemId")
    Long id;
    Long assetId;
    Double price;
    Double taxAmount;
    Double totalAmount;
    @ManyToOne
    @JoinColumn(columnDefinition = "BillId",nullable = false,referencedColumnName = "BillId")
    @JsonBackReference
    Bill bill;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(
            name = "BillItem_Tax",
            joinColumns = @JoinColumn(name = "bill_item_id"),
            inverseJoinColumns = @JoinColumn(name = "tax_id")
    )
    @JsonManagedReference
    private List<Tax> taxes;
}
