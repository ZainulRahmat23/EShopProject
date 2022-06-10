package com.miniProject.shop.Service.Implements;

import com.miniProject.shop.Dto.Product.ProductGridDto;
import com.miniProject.shop.Dto.Product.ProductUpsertdto;
import com.miniProject.shop.Repository.CategoryRepository;
import com.miniProject.shop.Repository.ProductRepository;
import com.miniProject.shop.Service.Inteface.CrudService;
import com.miniProject.shop.Service.Inteface.ProductService;
import com.miniProject.shop.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service("productMenu")
public class ProductServiceImplementation implements CrudService,ProductService {
    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public List<?> getAll() {
        var products = productRepository.findAll();
        var productGridDtos = products.stream()
                .map(product -> new ProductGridDto(
                        product.getId(),
                        product.getProduct(),
                        product.getCategoryName(),
                        product.getUnitPrice(),
                        product.getStock(),
                        product.getContinued()))
                .collect(Collectors.toList());
        return productGridDtos;
    }

    @Override
    public Object getById(Object id) {
        var products = productRepository.findById((Long)id).
                orElseThrow(()->new EntityNotFoundException("Product not found"));
        var grid = Stream.of(products)
                .map(product -> new ProductGridDto(
                        product.getId(),
                        product.getProduct(),
                        product.getCategoryName(),
                        product.getUnitPrice(),
                        product.getStock(),
                        product.getContinued()))
                .findFirst();
        return grid;
    }

    @Override
    public Object insert(Object object) {
        ProductUpsertdto dto = (ProductUpsertdto)object;
        var category = categoryRepository.findById(dto.getCategory())
                .orElseThrow(()-> new RuntimeException("Category not found"));
        var product = new Product(
                dto.getProduct(),
                category,
                dto.getPrice(),
                dto.getStock());
        return Stream.of(productRepository.save(product)).
                map(model -> new ProductGridDto(
                        model.getId(),
                        model.getProduct(),
                        model.getCategoryName(),
                        model.getUnitPrice(),
                        model.getStock(),
                        model.getContinued()));
    }

    @Override
    public Object update(Object id, Object object) {
        var dto = (ProductUpsertdto)object;
        var category = categoryRepository.findById(dto.getCategory())
                .orElseThrow(()-> new RuntimeException("Category not found"));

        var product = new Product((Long)id,
                dto.getProduct(),
                category,
                dto.getPrice(),
                dto.getStock());

        return Stream.of(productRepository.save(product)).
                map(model -> new ProductGridDto(
                        model.getId(),
                        model.getProduct(),
                        model.getCategoryName(),
                        model.getUnitPrice(),
                        model.getStock(),
                        model.getContinued()));
    }

    @Override
    public void delete(Object id) {
        var product = Stream.of(productRepository.findById((Long)id)
                .orElseThrow(()-> new RuntimeException("Product not found")))
                .filter(model -> {if(!model.getContinued()){
                    throw new RuntimeException("Product is not Available");}
                    return model.getContinued();
                })
                .map(model -> {
                    model.setContinued(false);
                    return model;
                })
                .findFirst().get();
        productRepository.save(product);

    }

}

