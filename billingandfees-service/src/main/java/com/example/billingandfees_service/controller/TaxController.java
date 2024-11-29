package com.example.billingandfees_service.controller;

import com.example.billingandfees_service.dto.request.TaxRequest;
import com.example.billingandfees_service.dto.response.ApiResponse;
import com.example.billingandfees_service.entity.Tax;
import com.example.billingandfees_service.service.TaxService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/tax")
public class TaxController {
    @Autowired
    private TaxService taxService;

    @GetMapping()
    public ApiResponse getTax() {
        ApiResponse apiResponse = ApiResponse.builder()
                .Code(200)
                .Message("Success")
                .Data(taxService.getAllTaxes())
                .build();
        return apiResponse;
    }
    @GetMapping("/{id}")
    public ApiResponse getTaxById(@PathVariable Long id) {
        ApiResponse apiResponse = ApiResponse.builder()
                .Code(200)
                .Message("Success")
                .Data(taxService.getTaxById(id))
                .build();
        return apiResponse;
    }
    @PostMapping("")
    public ApiResponse addTax(@RequestBody TaxRequest taxRequest) {
        ApiResponse apiResponse = ApiResponse.builder()
                .Code(200)
                .Message("Success")
                .Data(taxService.createTax(taxRequest))
                .build();
        return apiResponse;
    }
    @PutMapping("/{id}")
    public ApiResponse updateTax(@PathVariable Long id ,@RequestBody TaxRequest taxRequest) {
        ApiResponse apiResponse = ApiResponse.builder()
                .Code(200)
                .Message("Success")
                .Data(taxService.updateTax(id,taxRequest))
                .build();
        return apiResponse;
    }
    @DeleteMapping("/{id}")
    public ApiResponse deleteTax(@PathVariable Long id) {
        taxService.deleteTax(id);
        ApiResponse apiResponse = ApiResponse.builder()
                .Code(200)
                .Message("Success")
                .build();
        return apiResponse;
    }
}
