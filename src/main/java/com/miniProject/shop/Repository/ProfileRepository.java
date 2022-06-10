package com.miniProject.shop.Repository;

import com.miniProject.shop.Dto.Customer.ProfileOrderDto;
import com.miniProject.shop.Dto.Customer.TransactionHistory;
import com.miniProject.shop.model.Profile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


import java.util.List;

public interface ProfileRepository extends JpaRepository<Profile, Long> {
    @Query("""
            select new com.miniProject.shop.Dto.Customer.ProfileOrderDto(
                    p.id,
                    o.id,
                    p.product,
                    p.unitPrice,
                    od.quantity)
            from Profile as c
            left join c.orders as o
            left join o.orderDetails as od
            left join od.product as p
            where c.id = :id
            and o.status = 'Progress'
            """)
    List<ProfileOrderDto> findProfileOrder(Long id);

    @Query("""
            select new com.miniProject.shop.Dto.Customer.TransactionHistory(
                    p.id,
                    o.id,
                    p.product,
                    od.totalPrice,
                    od.quantity,
                    o.status)
            from Profile as c
            left join c.orders as o
            left join o.orderDetails as od
            left join od.product as p
            where c.id = :id
            and o.status != 'Progress'
            """)
    List<TransactionHistory> findProfileHistory(Long id);

//    @Query(value = """
//            select
//            p.Id as productId,
//            o.Id as orderId,
//            p.product as productName,
//            od.Price as totalPrice,
//            od.Quantity as quantity,
//            o.Status as status
//                       from Profiles as c
//                       left join Orders as o on c.Id = o.Customer
//                       left join OrderDetails as od on o.Id = od.OrderId
//                       left join Product as p on od.Product = p.Id
//                       where c.Id = :id
//                       and o.Status != 'Progress'
//            """,
//            nativeQuery = true)
//    List<Object> findAllOrder(Long id);
//

    @Query(name = "history",nativeQuery = true)
    List<TransactionHistory> findProfileTransactionHistory(Long id);

}

