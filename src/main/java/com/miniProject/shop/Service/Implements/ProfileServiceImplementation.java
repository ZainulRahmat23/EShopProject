package com.miniProject.shop.Service.Implements;

import com.miniProject.shop.Dto.Customer.*;
import com.miniProject.shop.Dto.RegisterAccountDto;
import com.miniProject.shop.Repository.ProfileRepository;
import com.miniProject.shop.Service.Inteface.ProfileService;
import com.miniProject.shop.Service.Inteface.UserRepository;
import com.miniProject.shop.model.Profile;
import com.miniProject.shop.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;

import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class ProfileServiceImplementation implements ProfileService {
    @Autowired
    private ProfileRepository profileRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public List<ProfileGridDto> getAllProfile() {

        List<ProfileGridDto> gridDtos = profileRepository.findAll()
                .stream()
                .map(customer -> new ProfileGridDto(
                        customer.getId(),
                        customer.fetchFullName(),
                        customer.getBirthDate(),
                        customer.getGender())).collect(Collectors.toList());
        return gridDtos;
    }

    @Override
    public Object getProfileByUsername(String username) {
        return Stream.of(userRepository.findByUsername(username).get())
                .map(user1 -> user1.getProfile())
                .map(profile -> new ProfileGridDto(
                        profile.getId(),
                        profile.fetchFullName(),
                        profile.getBirthDate(),
                        profile.getGender())).findFirst().get();
    }

    @Override
    public Object registerAccount(RegisterAccountDto dto) {
        var password = passwordEncoder.encode(dto.getPassword());
        User user = new User(dto.getUsername(), password, dto.getRole());
        Profile profile = new Profile(dto.getFirstName(), dto.getLastName(), dto.getBirthDate(), dto.getGender());
        user.setProfile(profile);
        profile.setUsers(user);
        return Stream.of(profileRepository.save(profile))
                .map(profile1 -> new ProfileGridDto(
                        profile1.getId(),
                        profile1.fetchFullName(),
                        profile1.getBirthDate(),
                        profile1.getGender())).findFirst().get();
    }

    @Override
    public Object updateAccount(RegisterAccountDto dto, String username) {
        User userOld = userRepository.findByUsername(username).get();
        var password = passwordEncoder.encode(dto.getPassword());
        User user = new User(userOld.getId(), dto.getUsername(), password, dto.getRole(), userOld.isEnabled());
        Profile profile = new Profile(user.getId() , dto.getFirstName(), dto.getLastName(), dto.getBirthDate(), dto.getGender());
        user.setProfile(profile);
        profile.setUsers(user);
        return Stream.of(profileRepository.save(profile))
                .map(profile1 -> new ProfileGridDto(
                        profile1.getId(),
                        profile1.fetchFullName(),
                        profile1.getBirthDate(),
                        profile1.getGender())).findFirst().get();
    }

    @Override
    public List<ProfileGridDto> getAllProfile(Long id) {
        var byId = profileRepository.findById(id).orElseThrow(() -> new RuntimeException("Customer not found"));
        List<ProfileGridDto> gridDtos = Stream.of(byId)
                .map(customer -> new ProfileGridDto(
                        customer.getId(),
                        customer.fetchFullName(),
                        customer.getBirthDate(),
                        customer.getGender())).toList();
        return gridDtos;
    }



    @Override
    public void saveProfile(UpsertProfileDto dto, Long id) {
        if (id != null){
            Profile customer = new Profile(
                    id, dto.getFirstName(), dto.getLastName(), dto.getBirthDate(), dto.getGender());
            profileRepository.save(customer);
        }else{
            Profile customer = new Profile(
                    dto.getFirstName(), dto.getLastName(), dto.getBirthDate(), dto.getGender());
            profileRepository.save(customer);
        }


    }


    @Override
    public List<ProfileOrderDto> profileOrder(String username) {
        User user = userRepository.findByUsername(username).get();
        return profileRepository.findProfileOrder(user.getId());
    };

    @Override
    public ProfileHistoryDto profileHistory(String username) {
        User user = userRepository.findByUsername(username).get();
//        var history = profileRepository.findProfileHistory(user.getId());
        var history = profileRepository.findProfileTransactionHistory(user.getId());
        return new ProfileHistoryDto(user.getProfile().fetchFullName(),  history);
    }

    @Override
    public boolean deleteProfile(Long id) {
        User user = userRepository.findById(id).orElseThrow(()-> new EntityNotFoundException("User not found"));
        if(!user.isEnabled()){
            throw new EntityNotFoundException("User not found");
        }
        user.setEnabled(false);
        userRepository.save(user);
            return true;
    }

}

