package com.miniProject.shop.RestController;

import com.miniProject.shop.Dto.Order.OrderGridDto;
import com.miniProject.shop.Dto.Order.OrderInsertDto;
import com.miniProject.shop.Service.Inteface.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/order")
public class OrderRestController {

    @Autowired
    private OrderService orderService;

    @GetMapping("/all")
    public List<OrderGridDto> getAllOrders() {
        return orderService.getAllOrders();
    }

    @PostMapping("/new-order")
    public Object insertOrder(Authentication authentication,
                                          @RequestBody OrderInsertDto dto) {
        String username = authentication.getName();
        return orderService.insertOrder(username,dto);
//        return orderService.getAllOrders();
    }

    @PutMapping("/confirm-order")
    public Object confirmOrder(@RequestParam("id") Integer id,Authentication authentication) {
        String username = authentication.getName();
        return orderService.confirmOrder(id,username);

    }




}
