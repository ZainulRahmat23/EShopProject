package com.miniProject.shop.Repository;

import com.miniProject.shop.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Integer> {

}

