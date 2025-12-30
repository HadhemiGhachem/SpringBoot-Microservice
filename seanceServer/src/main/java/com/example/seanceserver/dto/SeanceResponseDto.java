package com.example.seanceserver.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class SeanceResponseDto {
    private long id;
    private String title;
    private LocalDateTime startDate;
    private LocalDateTime endDate;


}
