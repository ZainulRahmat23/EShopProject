package com.miniProject.shop.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Table(name = "category")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id")
    private Integer id;

    @Column(name = "Category", nullable = false, length = 20)
    private String category;

//    @Lob
    @Column(name = "Description", nullable = false)
    private String description;

    @OneToMany(mappedBy = "category")
    private List<Product> products;

    public List<Long> getProductsId(){
        return products.stream().filter(p -> p.getContinued())
                .map(Product::getId).collect(Collectors.toList());
    }

    public Category(String category, String description) {
        this.category = category;
        this.description = description;
    }
}