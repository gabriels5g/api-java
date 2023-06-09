package com.example.refreshtoken.repositories;

import com.example.refreshtoken.models.ProfileModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;
@Repository
public interface ProfileRepository extends JpaRepository<ProfileModel, UUID> {
}
