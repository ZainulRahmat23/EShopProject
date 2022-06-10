package com.miniProject.shop.Service.Implements;

import com.miniProject.shop.Dto.ShipVia.ShipViaGridDto;
import com.miniProject.shop.Dto.ShipVia.ShipViaInsertDto;
import com.miniProject.shop.Repository.ShipViaRepository;
import com.miniProject.shop.Service.Inteface.CrudService;
import com.miniProject.shop.Service.Inteface.ShipViaService;
import com.miniProject.shop.model.ShipVia;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service("shipMenu")
public class ShipViaServiceImplements implements CrudService, ShipViaService {
    @Autowired
    private ShipViaRepository shipViaRepository;

    @Override
    public List<?> getAll() {
        return shipViaRepository.findAll().stream()
                .map(shipper -> new ShipViaGridDto(
                        shipper.getId(),
                        shipper.getCompanyName()))
                .collect(Collectors.toList());
//        return collect;
    }

    @Override
    public Object getById(Object id) {
        return Stream.of(shipViaRepository.findById((Integer)id).orElseThrow(()->new RuntimeException("Ship Not found")))
                .map(shipVia -> new ShipViaGridDto(shipVia.getId(), shipVia.getCompanyName()))
                .collect(Collectors.toList());
    }

    @Override
    public Object insert(Object object) {
        ShipViaInsertDto dto = ((ShipViaInsertDto) object);
        boolean exists = shipViaRepository.findAllByCompanyName(dto.getCompanyName()).isPresent();
        if(exists){
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "ShipVia already exists");
        }
        ShipVia ship = new ShipVia(dto.getCompanyName(),new BigDecimal(dto.getCost()));
        return  Stream.of(shipViaRepository.save(ship))
                .map(shipVia -> new ShipViaInsertDto(
                        shipVia.getCompanyName(),
                        shipVia.getCost().doubleValue())).collect(Collectors.toList());

    }

    @Override
    public Object update(Object id, Object object) {
        ShipViaInsertDto dto=((ShipViaInsertDto) object);
        var streamDto = Stream.of(dto).map(shipViaInsertDto -> {
            var old =shipViaRepository.findById((Integer) id)
                    .orElseThrow(()-> new RuntimeException("Ship Not Found"));
            old.setCompanyName(shipViaInsertDto.getCompanyName());
            old.setCost(new BigDecimal(shipViaInsertDto.getCost()));
            return shipViaRepository.save(old);})
                .collect(Collectors.toList());
        return streamDto.stream()
                .map(shipVia -> new ShipViaGridDto(shipVia.getId(), shipVia.getCompanyName())).findFirst();
    }

    @Override
    public void delete(Object id) {
    }

    @Override
    public List<?> cek() {
        return shipViaRepository.findAll();

    }
}
