package com.example.refreshtoken.controllers;

import com.example.refreshtoken.dto.ProfileDTO;
import com.example.refreshtoken.models.ProfileModel;
import com.example.refreshtoken.services.ProfileService;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin()
@RequestMapping("/profile")
public class ProfileController {

   final ProfileService profileService;

    public ProfileController(ProfileService profileService) {
        this.profileService = profileService;
    }

    @PostMapping()
    public ResponseEntity<Object> createProfile(@RequestBody @Valid ProfileDTO profileDTO) {
       var profileModel = new ProfileModel();
        BeanUtils.copyProperties(profileDTO, profileModel);

        return  ResponseEntity.status(HttpStatus.CREATED).body(profileService.save(profileModel));
    }


}
