package com.miniProject.shop.Dto.ShipVia;

import com.miniProject.shop.Dto.Order.OrderGridDto;
import com.miniProject.shop.model.Order;
import lombok.Data;

import java.util.List;

@Data
public class ShipViaGridDto {
    private final Integer id;
    private final String company;
//    private final List<Integer> orderIds;

}
