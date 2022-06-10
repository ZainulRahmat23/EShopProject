package com.miniProject.shop.Repository;

import com.miniProject.shop.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product,Long> {
    @Query(value = """
           
            Update Product as p
            set p.Continued = 0
            where p.Id = :id
            """,nativeQuery = true)
    void deleteById(Long id);

    @Query(value = """
            select * from product as p
            where p.Id in (:ids)
            """,nativeQuery = true)
    List<Product> findAllById(List<Long> ids);

    @Query(value = """
            select * from product p
            where p.continued = 1
            or p.continued = null""", nativeQuery = true)
    List<Product> findAll();
}
