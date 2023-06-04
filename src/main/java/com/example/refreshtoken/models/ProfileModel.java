package com.example.refreshtoken.models;

import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table(name = "TB_PROFILE")
public class ProfileModel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    @Column()
    private String userName;
    @Column()
    private String bio;
    @Column()
    private String avatarUrl;

    @OneToOne
    private UserModel userModel;
}
