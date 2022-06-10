package com.miniProject.shop.Dto;

import com.miniProject.shop.Helper.Helper;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Setter
@Getter
public class RegisterAccountDto {
    private String username;
    private String password;
    private String role;
    private String firstName;
    private String lastName;
    private String birthDate;
    private String gender;


    public LocalDate getBirthDate(){
        return Helper.Date.format(birthDate);
    }
}
