package com.miniProject.shop.Dto.Customer;

import lombok.Data;

import java.time.LocalDate;

@Data
public class ProfileGridDto {
    private final Long id;
    private final String fullName;
    private final LocalDate birthDate;
    private final String gender;
}
