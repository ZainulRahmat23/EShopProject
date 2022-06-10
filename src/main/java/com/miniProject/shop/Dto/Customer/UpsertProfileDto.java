package com.miniProject.shop.Dto.Customer;

import com.miniProject.shop.Helper.Helper;
import lombok.Data;
import java.time.LocalDate;

@Data
public class UpsertProfileDto {
//    private Long id;
    private String firstName;
    private String lastName;
    private String birthDate;
    private String gender;


    public LocalDate getBirthDate(){
        return Helper.Date.format(birthDate);
    }

}
