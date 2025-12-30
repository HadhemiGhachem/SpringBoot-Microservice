package com.example.coachserver.services;

import com.example.coachserver.Repository.CoachRepository;
import com.example.coachserver.dto.CoachDto;
import com.example.coachserver.dto.CoachResponseDto;
import com.example.coachserver.entities.Coach;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class CoachServiceImpl implements CoachService {

    private final CoachRepository coachRepository;

    public CoachServiceImpl(CoachRepository coachRepository) {
        this.coachRepository = coachRepository;
    }

    @Override
    public Coach createCoach(CoachDto coachDto, int user_id) {
        Coach coach = new Coach();
        coach.setSpeciality(coachDto.getSpeciality());
        coach.setEmail(coachDto.getEmail());
        coach.setFirstName(coachDto.getFirstName());
        coach.setLastName(coachDto.getLastName());
        coach.setUser_id(user_id);
        return coachRepository.save(coach);
    }

    @Override
    public Coach updateCoach(Long id, CoachDto coachDto,int user_id) {
        Coach existing = coachRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Coach not found"));
        if (user_id != existing.getUser_id()) {
            throw new ResponseStatusException(
                    HttpStatus.FORBIDDEN, "You are not authorized to update this coach");
        }
        existing.setFirstName(coachDto.getFirstName());
        existing.setLastName(coachDto.getLastName());
        existing.setSpeciality(coachDto.getSpeciality());
        existing.setEmail(coachDto.getEmail());

        return coachRepository.save(existing);
    }

    @Override
    public void deleteCoach(Long id) {
        if (!coachRepository.existsById(id)) {
            throw new RuntimeException("Coach not found");
        }
        coachRepository.deleteById(id);
    }

    @Override
    public CoachResponseDto getCoachById(Long id) {
        Coach coach= coachRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Coach not found"));
        CoachResponseDto coachResponseDto = new CoachResponseDto();
        coachResponseDto.setEmail(coach.getEmail());
        coachResponseDto.setSpeciality(coach.getSpeciality());
        coachResponseDto.setFirstName(coach.getFirstName());
        coachResponseDto.setLastName(coach.getLastName());
        coachResponseDto.setId(coach.getId());
        return  coachResponseDto;
        }

    @Override
    public List<CoachResponseDto> getAllCoaches() {
        List<Coach> coaches= coachRepository.findAll();
        List<CoachResponseDto> coachResponseDtoList = coaches.stream().map((coach -> new CoachResponseDto(coach.getId(),coach.getFirstName(),coach.getLastName(),coach.getSpeciality(),coach.getEmail()))).toList();
        return coachResponseDtoList;
    }
}