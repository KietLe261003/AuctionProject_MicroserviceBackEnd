package com.example.billingandfees_service.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Bill {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "BillId")
    Long id;
    Long userId;
    Long staffId;
    LocalDate billDate;
    Double totalAmount;
    LocalDate paymentTerm;
    Boolean paymentStatus;

    @OneToMany(mappedBy = "bill",cascade = CascadeType.ALL)
    @JsonManagedReference
    List<BillItem> billItems;
}
