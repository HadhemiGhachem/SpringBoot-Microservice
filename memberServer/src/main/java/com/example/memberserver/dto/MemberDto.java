package com.example.memberserver.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class MemberDto {

    private String firstName;

    private String lastName;

    private String email;

    private String phone;

    private String status;

}
