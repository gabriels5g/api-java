package com.example.refreshtoken.dto;

import com.example.refreshtoken.models.UserModel;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;


public class ProfileDTO {
    @NotBlank
    private String userName;
    @Size(min = 5, max = 255)
    private String bio;

    @NotBlank
    private String avatarUrl;

    @NotBlank
    private UserModel userModel;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    public UserModel getUserModel() {
        return userModel;
    }

    public void setUserModel(UserModel userModel) {
        this.userModel = userModel;
    }
}
