package com.miniProject.shop.Dto.Customer;

import lombok.Data;

import java.util.List;

@Data
public class ProfileHistoryDto {
    private String customer;
    private List<TransactionHistory> products;

    public ProfileHistoryDto(String customer, List<TransactionHistory> products) {
        this.customer = customer;
        this.products = products;
    }


}
