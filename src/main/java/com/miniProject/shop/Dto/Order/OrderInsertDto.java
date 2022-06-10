package com.miniProject.shop.Dto.Order;

import lombok.Data;

import java.util.List;

@Data
public class OrderInsertDto {
//    private final Long customerId;
    private final Integer shipViaId;
    private final List<Long> products;
//    pastikan length products >= length quantities
    private final List<Integer> quantities;

}
