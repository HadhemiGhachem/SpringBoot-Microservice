package com.example.seanceserver.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class SeanceDto {
    private String title;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private Long coachId;


}
