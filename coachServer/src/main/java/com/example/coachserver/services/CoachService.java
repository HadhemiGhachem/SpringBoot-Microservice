package com.example.coachserver.services;


import com.example.coachserver.dto.CoachDto;
import com.example.coachserver.dto.CoachResponseDto;
import com.example.coachserver.entities.Coach;

import java.util.List;

public interface CoachService {

    Coach createCoach(CoachDto coach, int user_id);

    Coach updateCoach(Long id, CoachDto coachDto,int user_id);

    void deleteCoach(Long id);

    CoachResponseDto getCoachById(Long id);

    List<CoachResponseDto> getAllCoaches();
}