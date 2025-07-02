package com.example.demo.data.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SignupRequest {
    @NotBlank
    @Size(max = 50)
    @Email
    private String email;

    @NotBlank
    @Size(min = 8, message = "Password must be at least 8 characters long")
    @Pattern(regexp = "^(?=.*[0-9])(?=.*[^A-Za-z0-9]).*$", message = "Password must contain at least one number and one non-alphanumeric character")
    private String password;

    @NotBlank(message = "Country cannot be blank")
    private String country;

    @NotBlank(message = "City cannot be blank")
    private String city;

    private String photoUrl;
}