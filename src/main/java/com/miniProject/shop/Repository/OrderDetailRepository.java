package com.miniProject.shop.Repository;

import com.miniProject.shop.model.OrderDetail;
import com.miniProject.shop.model.OrderDetailId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderDetailRepository extends JpaRepository<OrderDetail, OrderDetailId> {

}
