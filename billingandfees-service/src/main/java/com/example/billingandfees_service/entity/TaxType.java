package com.example.billingandfees_service.entity;

public enum TaxType {
    Percentage("P"),
    Fixed("F"),
    ;
    String type;
    TaxType(String type) {
        this.type = type;
    }

}
