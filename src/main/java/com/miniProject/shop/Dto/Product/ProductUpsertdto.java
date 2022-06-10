package com.miniProject.shop.Dto.Product;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class ProductUpsertdto {
//    private  Long id;
    private  String product;
    private  Integer category;
    private  BigDecimal price;
    private  Integer stock;

}
