package com.example.coachserver.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class CoachResponseDto {
    private Long id;

    private String firstName;

    private String lastName;

    private String speciality;

    private String email;
}
