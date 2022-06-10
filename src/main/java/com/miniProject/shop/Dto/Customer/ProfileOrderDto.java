package com.miniProject.shop.Dto.Customer;

import com.miniProject.shop.Helper.Helper;
import lombok.Data;

import java.math.BigDecimal;

//@Getter
//@Setter
@Data
public class ProfileOrderDto {
    private Long productId;
    private Integer orderId;
    private String productName;
    private String priceUnit;
    private Integer quantity;

    public ProfileOrderDto(Long productId, Integer orderId, String productName, BigDecimal price, Integer quantity) {
        this.productId = productId;
        this.orderId = orderId;
        this.productName = productName;
        this.priceUnit = Helper.Money.getMoney(price);
        this.quantity = quantity;
    }
}
