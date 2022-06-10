package com.miniProject.shop.Dto.ShipVia;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Getter
@Setter
public class ShipViaInsertDto {
    private String companyName;
    private double cost;
}
