package com.example.refreshtoken.services;

import com.example.refreshtoken.models.ProfileModel;
import com.example.refreshtoken.repositories.ProfileRepository;
import org.springframework.stereotype.Service;

@Service
public class ProfileService {

    final ProfileRepository profileRepository;

    public ProfileService(ProfileRepository profileRepository) {
        this.profileRepository = profileRepository;
    }

    public ProfileModel save(ProfileModel profile) {
        ProfileModel newProfile = profileRepository.save(profile);

        return newProfile;
    }
}
