package com.example.Coffline.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserRequest {

    @NotBlank(message = "Ad alanı boş olamaz")
    private String name;

    @NotBlank(message = "Soyad alanı boş olamaz")
    private String surName;

    @Email
    @NotBlank(message = "E-posta alanı boş olamaz")
    private String email;

    @NotBlank(message = "Password alanı boş olamaz")
    private String password;
}
