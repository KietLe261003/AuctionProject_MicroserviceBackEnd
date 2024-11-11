package com.example.billingandfees_service.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class Tax {
    @Id
    Long id;
    String taxName;
    String taxDescription;
    Double taxAmount;
    Boolean delflag;
    @Enumerated(EnumType.STRING)
    TaxType taxType;
    @ManyToMany(mappedBy = "taxes", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JsonBackReference
    private List<BillItem> billItems;

}
