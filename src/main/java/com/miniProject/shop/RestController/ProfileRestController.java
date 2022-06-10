package com.miniProject.shop.RestController;

import com.miniProject.shop.Dto.Customer.ProfileHistoryDto;
import com.miniProject.shop.Dto.Customer.ProfileOrderDto;
import com.miniProject.shop.Dto.Customer.UpsertProfileDto;
import com.miniProject.shop.Dto.RegisterAccountDto;
import com.miniProject.shop.Service.Inteface.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/account")
public class ProfileRestController {

    @Autowired
    private ProfileService profileService;


/*
* register/add
* update profile
* get profile
* get profile history
* get profile order
* delete profile sekaligus disable user tsb
* */
    @GetMapping("/all")
    public List<?> getAllProfile() {
        return profileService.getAllProfile();
    }

    @GetMapping("/profile")
    public Object getProfileByUsername(Authentication authentication) {
        String username = authentication.getName();
        return profileService.getProfileByUsername(username);
    }

    @PostMapping("/register")
    public Object saveProfile(@RequestBody RegisterAccountDto dto) {
        return profileService.registerAccount(dto);
    }

    @PutMapping("/update")
    public Object  updateProfile(Authentication authentication, @RequestBody RegisterAccountDto dto) {
        String username = authentication.getName();
        return profileService.updateAccount(dto, username);

    }

    @GetMapping("/order")
    public List<?> customerOrder(Authentication authentication) {
        authentication.getName();
        return profileService.profileOrder(authentication.getName());
    }

    @GetMapping("/history")
    public ProfileHistoryDto customerHistory(Authentication authentication) {

        return profileService.profileHistory(authentication.getName());
    }



//    @GetMapping("/index")
//    public List<ProfileGridDto> getAllCustomers(@RequestParam(value="id",required = false) Long id) {
//        if(id == null) {
//            return profileService.getAllProfile();
//        }
//        else {
//            return profileService.getAllProfile(id);
//        }
//
//    }
//
  //
    @DeleteMapping("/delete")
    public boolean deleteCustomer(@RequestParam(value = "id") Long id) {
        return profileService.deleteProfile(id);

    }
//

//



}
