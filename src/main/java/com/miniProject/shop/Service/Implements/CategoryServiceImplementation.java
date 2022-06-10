package com.miniProject.shop.Service.Implements;

import com.miniProject.shop.Dto.Category.CategoryGridDto;
import com.miniProject.shop.Dto.Category.CategoryUpsertDto;
import com.miniProject.shop.Repository.CategoryRepository;
import com.miniProject.shop.Service.Inteface.CategoryService;
import com.miniProject.shop.Service.Inteface.CrudService;
import com.miniProject.shop.model.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service("categoryService")
public class CategoryServiceImplementation implements CrudService, CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public List<?> getAll() {
        var categories = categoryRepository.findAll();
        return categories.stream()
                .map(category ->
                        new CategoryGridDto(
                                category.getId(),
                                category.getCategory(),
                                category.getDescription(),
                                category.getProductsId())
                ).collect(Collectors.toList());
    }

    @Override
    public Object getById(Object id) {
        var a = Integer.valueOf((String) id);
        var category = categoryRepository.findById(a)
                .orElseThrow(() -> new RuntimeException("Category not found"));

        var cek = Stream.of(category)
                .map(category1 ->
                        new CategoryGridDto(
                                category1.getId(),
                                category1.getCategory(),
                                category1.getDescription(),
                                category1.getProductsId())
                ).findFirst();
        return cek;
    }

    @Override
    public Object insert(Object object) {
        CategoryUpsertDto dto = ((CategoryUpsertDto) object);
        boolean exists = categoryRepository.findAllByCategory(dto.getCategory()).isPresent();
        if(exists){
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Category already exists");
        }

        Category category = new Category(dto.getCategory(), dto.getDescription());
        return Stream.of(categoryRepository.save(category))
                .map(cek -> new CategoryUpsertDto(cek.getCategory(), cek.getDescription())).findFirst();


    }

    @Override
    public Object update(Object id, Object object) {
        var category = categoryRepository.findById(Integer.valueOf((String) id))
                .orElseThrow(() -> new RuntimeException("Category not found"));
        CategoryUpsertDto dto = ((CategoryUpsertDto) object);
        category.setCategory(dto.getCategory());
        category.setDescription(dto.getDescription());
        return Stream.of(categoryRepository.save(category))
                .map(cek -> new CategoryUpsertDto(cek.getCategory(), cek.getDescription())).findFirst();

    }

    @Override
    public void delete(Object id) {
    }
}
