package com.miniProject.shop.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.Hibernate;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Entity;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
@Data
@NoArgsConstructor
public class OrderDetailId implements Serializable {
    private static final long serialVersionUID = 2748308948315725278L;
    @Column(name = "OrderId", nullable = false)
    private Integer orderId;

    @Column(name = "Product", nullable = false)
    private Long product;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        OrderDetailId entity = (OrderDetailId) o;
        return Objects.equals(this.product, entity.product) &&
                Objects.equals(this.orderId, entity.orderId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(product, orderId);
    }

    public OrderDetailId(Integer orderId, Long product) {
        this.orderId = orderId;
        this.product = product;
    }
}