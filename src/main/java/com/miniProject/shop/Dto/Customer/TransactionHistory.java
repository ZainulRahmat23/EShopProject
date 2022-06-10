package com.miniProject.shop.Dto.Customer;

import com.miniProject.shop.Helper.Helper;
import lombok.Data;

import java.math.BigDecimal;

@Data
//@AllArgsConstructor
//@NoArgsConstructor
public class TransactionHistory {
    private Long productId;
    private Integer orderId;
    private String productName;
    private String totalPrice;
    private Integer quantity;
    private String status;

    public TransactionHistory(Long productId, Integer orderId, String productName, BigDecimal totalPrice, Integer quantity, String status) {
        this.productId = productId;
        this.orderId = orderId;
        this.productName = productName;
        this.totalPrice = Helper.Money.getMoney(totalPrice);
        this.quantity = quantity;
        this.status = status;
    }

}
