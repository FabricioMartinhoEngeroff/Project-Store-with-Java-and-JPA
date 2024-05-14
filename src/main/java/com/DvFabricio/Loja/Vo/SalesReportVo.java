package com.DvFabricio.Loja.Vo;

import lombok.Getter;

import java.time.LocalDate;

@Getter
public class SalesReportVo {

    private String productName;
    private Long soldQuantity;
    private LocalDate lastSaleDate;

    public SalesReportVo(String productName, Long soldQuantity, LocalDate lastSaleDate) {
        this.productName = productName;
        this.soldQuantity = soldQuantity;
        this.lastSaleDate = lastSaleDate;
    }

    @Override
    public String toString() {
        return "SalesReportVo{" +
                "productName='" + productName + '\'' +
                ", soldQuantity=" + soldQuantity +
                ", lastSaleDate=" + lastSaleDate +
                '}';
    }
}
