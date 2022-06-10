package com.miniProject.shop.Dto.Order;

import com.miniProject.shop.Helper.Helper;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class OrderGridDto {
    private final Integer orderId;
    private final String customerName;
    private final String shipViaName;
    private final String status;
    private final String price;



}
