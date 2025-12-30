package com.example.userserver.dto;

import lombok.Data;

@Data
public class SignupDTO {
    private String username;
    private String email;
    private String password;
    private String role = "USER";   // default
}

