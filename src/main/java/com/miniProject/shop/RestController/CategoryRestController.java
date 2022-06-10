package com.miniProject.shop.RestController;

import com.miniProject.shop.Dto.Category.CategoryUpsertDto;
import com.miniProject.shop.Service.Inteface.CategoryService;
import com.miniProject.shop.Service.Inteface.CrudService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/category")
public class CategoryRestController {
    @Qualifier("categoryService")
    @Autowired
    private CrudService service;

    @Autowired
    private CategoryService categoryService;

    @GetMapping("/getAll")
    public List<?> getAll() {
        return service.getAll();
    }

    @GetMapping("/getById/{id}")
    public Object getById(@PathVariable Object id) {
        return service.getById(id);
    }

    @PostMapping("/insert")
    public Object insert(@RequestBody CategoryUpsertDto object) {
        return service.insert(object);
    }

    @PutMapping("/update")
    public Object update(@RequestParam Object id,@RequestBody CategoryUpsertDto object) {
        return service.update(id, object);
    }

}
