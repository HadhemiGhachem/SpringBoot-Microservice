package com.example.userserver.dto;

import lombok.Data;

@Data
public class LoginDTO {
    private String email;    // Changed from username
    private String password;
}
