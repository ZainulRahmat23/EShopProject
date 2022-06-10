package com.miniProject.shop.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Table(name = "ShipVia")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ShipVia {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id", nullable = false)
    private Integer id;

    @Column(name = "CompanyName", nullable = false, length = 30)
    private String companyName;

    @Column(name = "Cost", precision = 19, scale = 4)
    private BigDecimal cost;

//    @JsonIgnore
    @OneToMany(mappedBy = "shipper")
    private List<Order> orders;

    public List<Integer> getListOrder(){
        return orders.stream().map(order -> order.getId()).collect(Collectors.toList());
    }

    public ShipVia(String companyName, BigDecimal cost) {
        this.companyName = companyName;
        this.cost = cost;
    }


    public ShipVia(Integer id, String companyName, BigDecimal cost) {
        this.id = id;
        this.companyName = companyName;
        this.cost = cost;
    }
}