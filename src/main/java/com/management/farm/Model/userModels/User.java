package com.management.farm.Model.userModels;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@Table(name = "app_Users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Column(unique = true)
    private String email;

    @Column(unique = true)
    private String phoneNumber;

    private String password;
    
}
