package com.example.coachserver.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;



@Data
@AllArgsConstructor
@NoArgsConstructor

public class CoachDto {

    private String firstName;

    private String lastName;

    private String speciality;

    private String email;

}

