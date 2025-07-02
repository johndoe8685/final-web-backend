package com.example.demo.data.dto;

import lombok.Data;

@Data
public class UserDto {
    private Long id;
    private String email;
    private String country;
    private String city;
    private String photoUrl;
}