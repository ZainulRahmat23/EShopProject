package com.miniProject.shop.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "Product")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id", nullable = false)
    private Long id;

    @Column(name = "Product", nullable = false, length = 25)
    private String product;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "Category", nullable = false)
    private Category category;

    @Column(name = "UnitPrice", nullable = false, precision = 19, scale = 4)
    private BigDecimal unitPrice;

    @Column(name = "Stock", nullable = false)
    private Integer stock;

    @OneToMany(mappedBy = "product")
    private List<OrderDetail> orderDetails;

    @Column(name = "Continued")
    private Boolean continued;

    public String getCategoryName() {
        return category.getCategory();
    }

    public Product(Long id, String product, Category category, BigDecimal unitPrice, Integer stock) {
        this.id = id;
        this.product = product;
        this.category = category;
        this.unitPrice = unitPrice;
        this.stock = stock;
        this.continued = true;
    }

    public Product(String product, Category category, BigDecimal unitPrice, Integer stock) {
        this.product = product;
        this.category = category;
        this.unitPrice = unitPrice;
        this.stock = stock;
        this.continued = true;

    }
}