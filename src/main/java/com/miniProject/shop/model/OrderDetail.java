package com.miniProject.shop.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "OrderDetails")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class OrderDetail {
    @EmbeddedId
    private OrderDetailId id;

    @MapsId("orderId")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "OrderId", nullable = false)
    private Order order;

    @MapsId("product")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "Product", nullable = false)
    private Product product;

    @Column(name = "Quantity", nullable = false)
    private Integer quantity;

    @Column(name = "Price")
    private BigDecimal totalPrice;

    public OrderDetail(Order order, Product product, Integer quantity) {
        this.id = new OrderDetailId(order.getId(), product.getId());
        this.order = order;
        this.product = product;
        this.quantity = quantity;

    }

    public OrderDetail(Product product, Integer quantity) {
        this.product = product;
        this.quantity = quantity;
    }
}