package com.miniProject.shop.RestController;

import com.miniProject.shop.Dto.Product.ProductGridDto;
import com.miniProject.shop.Dto.Product.ProductUpsertdto;
import com.miniProject.shop.Service.Inteface.CrudService;
import com.miniProject.shop.Service.Inteface.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductRestController {
    @Qualifier("productMenu")
    @Autowired
    CrudService service;

    @Autowired
    private ProductService productService;

    @GetMapping("/all")
    public List<?> getAll() {
        return service.getAll();
    }

    @GetMapping("/getById/{id}")
    public Object getById(@PathVariable Long id) {
        return service.getById(id);
    }

    @PostMapping("/insert")
    public Object insert(@RequestBody ProductUpsertdto dto) {
        return service.insert(dto);
    }

    @PutMapping("/update/{id}")
    public Object update(@PathVariable Long id, @RequestBody ProductUpsertdto dto) {
        return service.update(id, dto);
    }

    @DeleteMapping("/delete/{id}")
    public List<?> delete(@PathVariable Long id) {
        service.delete(id);
        return service.getAll();
    }

}
