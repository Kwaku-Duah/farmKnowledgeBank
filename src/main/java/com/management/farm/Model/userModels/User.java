package com.management.farm.Model.userModels;

import com.fasterxml.jackson.annotation.JsonIgnore;

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

    private String phoneNumber;

    // perfect to avoid seriliazing the password when converting user object to json
    @JsonIgnore
    private String password;
    
}
