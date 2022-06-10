package com.miniProject.shop.Service.Implements;

import com.miniProject.shop.Dto.Order.OrderGridDto;
import com.miniProject.shop.Dto.Order.OrderInsertDto;
import com.miniProject.shop.Helper.Helper;
import com.miniProject.shop.Repository.*;
import com.miniProject.shop.Service.Inteface.OrderService;
import com.miniProject.shop.Service.Inteface.UserRepository;
import com.miniProject.shop.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;


@Service
public class OrderServiceImplementation implements OrderService {
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private OrderDetailRepository orderDetailRepository;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private ShipViaRepository shipViaRepository;
    //    @Autowired
//    private CustomerRepository customerRepository;
    @Autowired
    private ProfileRepository profileRepository;
    @Autowired
    private UserRepository userRepository;

    @Override
    public List<OrderGridDto> getAllOrders() {
        List<Order> orders = orderRepository.findAll();
        List<OrderGridDto> collect = orders.stream().map(order ->
                        new OrderGridDto(order.getId(),
                                order.getCustomerName(),
                                order.getShipperName(),
                                order.getStatus(),
                                order.getTotalPrice() == null ? "0" : Helper.Money.getMoney(order.getTotalPrice())))
                .collect(Collectors.toList());
        return collect;
    }

    @Override
    public Object insertOrder(String username, OrderInsertDto dto) {
        User customer = userRepository.findByUsername(username).orElseThrow(() -> new RuntimeException("User not found"));

        ShipVia shipper = shipViaRepository.findById(dto.getShipViaId())
                .orElseThrow(() -> new RuntimeException("Shipper not found"));
        Order order = new Order(customer.getProfile(), shipper);
        orderRepository.save(order);
        return insertDetail(order, dto.getProducts(), dto.getQuantities());
    }

    public Object insertDetail(Order order, List<Long> products, List<Integer> quantities) {
        if (products.size() != quantities.size())
            throw new RuntimeException("Product and quantity not match");

        var productList = products.stream()
                .map(productId -> productRepository.findById(productId)
                        .orElseThrow(() -> new RuntimeException("Product not found")))
                .filter(prods -> {
                    if (!prods.getContinued()) {
                        errorInput(order, "Error");
                        throw new RuntimeException(String.format("Product with id: %s Not Available", prods.getId()));
                    }
                    return prods.getContinued();
                })
                .collect(Collectors.toList());


        for (int i = 0; i < productList.size(); i++) {
            OrderDetail details = new OrderDetail(order, productList.get(i), quantities.get(i));
            orderDetailRepository.save(details);
        }
        return Stream.of(orderRepository.save(order))
                .map(save->new OrderGridDto(
                        save.getId(),
                        save.getCustomerName(),
                        save.getShipperName(),
                        save.getStatus(),
                        save.getTotalPrice() == null ? "0" : Helper.Money.getMoney(save.getTotalPrice())))
                .findFirst().get();

    }

    private void errorInput(Order order, String message) {
        order.setStatus(message);
        order.setTotalPrice(new BigDecimal(0.0));
        orderRepository.save(order);
    }

    @Override
    public Object confirmOrder(Integer id, String username) {
        var orderList = Stream.of(userRepository.findByUsername(username).get())
                .map(user -> user.getProfile().getOrders()).findFirst().get();
        var order = orderList.stream()
                .filter(ords->ords.getStatus().equals("Progress"))
                .filter(o -> o.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Order not found"));
        order.setStatus("Confirmed");
        var details = order.getOrderDetails();
        var totalPrice = 0.0;

            for (OrderDetail detail : details) {
            Product product = detail.getProduct();
            BigDecimal unitPrice = product.getUnitPrice();
            Integer quantity = detail.getQuantity();
            Integer stock = product.getStock();
            if (quantity > stock) {
                errorInput(order, "Not Enough Stock");
                detail.setTotalPrice(new BigDecimal(0.0));
                throw new RuntimeException(String.format("Stock %s tidak mencukupi", product.getProduct()));
            }
            product.setStock(stock - quantity);
            productRepository.save(product);
            BigDecimal multiply = unitPrice.multiply(new BigDecimal(quantity));
            detail.setTotalPrice(multiply);
            totalPrice += multiply.doubleValue();
        }
        BigDecimal cost = order.getShipper().getCost();
        totalPrice += cost.doubleValue();
        order.setTotalPrice(new BigDecimal(totalPrice));
        return Stream.of(orderRepository.save(order))
                .map(save->new OrderGridDto(
                        save.getId(),
                        save.getCustomerName(),
                        save.getShipperName(),
                        save.getStatus(),
                        save.getTotalPrice() == null ? "0" : Helper.Money.getMoney(save.getTotalPrice())))
                .findFirst().get();
    }
}
