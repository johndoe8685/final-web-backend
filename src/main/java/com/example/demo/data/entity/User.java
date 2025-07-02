package com.example.demo.data.entity;

import java.util.HashSet;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "users", 
       uniqueConstraints = { 
           @UniqueConstraint(columnNames = "email") 
       })
@Data
@NoArgsConstructor
public class User {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; 

    @NotBlank
    @Size(max = 50)
    @Email
    private String email;

    @NotBlank
    private String password;

    @NotBlank
    private String country;

    @NotBlank
    private String city;

    private String photoUrl;

    public User(String email, String password, String country, String city) {
        this.email = email;
        this.password = password;
        this.country = country;
        this.city = city;
    }
}
