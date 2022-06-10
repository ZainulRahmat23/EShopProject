package com.miniProject.shop.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;

@Entity
@Table(name = "Orders")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id", nullable = false)
    private Integer id;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "Customer", nullable = false)
    private Profile customer;


    @Column(name = "[Status]", length = 30)
    private String status;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "Shipper")
    private ShipVia shipper;

    @Column(name = "TotalPrice")
    private BigDecimal totalPrice;


    @JsonIgnore
    @OneToMany(mappedBy = "order",cascade = CascadeType.ALL)
    private List<OrderDetail> orderDetails;

    public String getCustomerName() {
        return customer.fetchFullName();
    }

    public String getShipperName() {
        return shipper.getCompanyName();
    }

    public Order(Profile customer, ShipVia shipper) {
        this.customer = customer;
        this.shipper = shipper;
        this.status = "Progress";
    }
}