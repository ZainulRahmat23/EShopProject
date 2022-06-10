package com.miniProject.shop.Dto.Category;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class CategoryUpsertDto {
    private String category;
    private String description;
}
