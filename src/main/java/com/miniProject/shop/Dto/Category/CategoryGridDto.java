package com.miniProject.shop.Dto.Category;

import lombok.Data;

import java.util.List;

@Data
public class CategoryGridDto {
    private final Integer id;
    private final String category;
    private final String description;
    private final List<Long> products;
}
