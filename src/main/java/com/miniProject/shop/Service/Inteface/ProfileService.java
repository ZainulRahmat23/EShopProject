package com.miniProject.shop.Service.Inteface;

import com.miniProject.shop.Dto.Customer.ProfileGridDto;
import com.miniProject.shop.Dto.Customer.ProfileHistoryDto;
import com.miniProject.shop.Dto.Customer.ProfileOrderDto;
import com.miniProject.shop.Dto.Customer.UpsertProfileDto;
import com.miniProject.shop.Dto.RegisterAccountDto;

import java.util.List;

public interface ProfileService {

    public List<ProfileGridDto> getAllProfile();
    public Object getProfileByUsername (String username);
    public Object registerAccount(RegisterAccountDto dto);
    public Object updateAccount(RegisterAccountDto dto, String username);
    public List<ProfileGridDto> getAllProfile(Long id);
//    public void saveCustomer(UpsertCustomerDto dto);
    public void saveProfile(UpsertProfileDto dto, Long id);
    public boolean deleteProfile(Long id);
    public List<ProfileOrderDto> profileOrder(String username);
    public ProfileHistoryDto profileHistory(String username);

}
