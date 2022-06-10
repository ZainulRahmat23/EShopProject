package com.miniProject.shop.RestController;

import com.miniProject.shop.Dto.ShipVia.ShipViaGridDto;
import com.miniProject.shop.Dto.ShipVia.ShipViaInsertDto;
import com.miniProject.shop.Service.Inteface.CrudService;
import com.miniProject.shop.Service.Inteface.ShipViaService;
import com.miniProject.shop.model.ShipVia;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/shipper")
public class ShipViaRestController {
    @Qualifier("shipMenu")
    @Autowired
    private CrudService service;

    @Autowired
    private ShipViaService shipViaService;

    @GetMapping("/getAll")
    public List<?> getAll(){
        return service.getAll();
    }

    @GetMapping("/getById")
    public Object getById(@RequestParam Integer id){
        return service.getById(id);
    }

    @PostMapping("/insert")
    public Object insert(@RequestBody ShipViaInsertDto dto){
        return service.insert(dto);
    }

    @PutMapping("/update")
    public Object update(@RequestBody ShipViaInsertDto dto,@RequestParam Integer id){
        return service.update(id,dto);
    }

    @DeleteMapping("/delete")
    public void delete(@RequestParam Integer id){
        service.delete(id);
    }

    @GetMapping("/cek")
    public List<?> cek(){
        return shipViaService.cek();
    }
}
