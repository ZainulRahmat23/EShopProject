package com.miniProject.shop.Repository;

import com.miniProject.shop.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CategoryRepository extends JpaRepository<Category, Integer> {
    Optional<Category> findAllByCategory(String category);

}

