package com.miniProject.shop.Dto.Product;

import com.miniProject.shop.Helper.Helper;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class ProductGridDto {
    private final Long id;
    private final String product;
    private final String category;
    private final BigDecimal price;
    private final Integer stock;
    private final Boolean continued;

    public String getPrice(){
        return Helper.Money.getMoney(price);
    }
}
