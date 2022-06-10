package com.miniProject.shop.Service.Inteface;

import com.miniProject.shop.Dto.Order.OrderGridDto;
import com.miniProject.shop.Dto.Order.OrderInsertDto;
import com.miniProject.shop.model.Order;

import java.util.List;

public interface OrderService {

    public List<OrderGridDto> getAllOrders();
    public Object insertOrder(String username, OrderInsertDto dto);
    public Object confirmOrder(Integer id, String username);

}
