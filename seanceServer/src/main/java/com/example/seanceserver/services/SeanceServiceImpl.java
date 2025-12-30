package com.example.seanceserver.services;

import com.example.seanceserver.dto.CoachResponseDto;
import com.example.seanceserver.dto.MemberResponseDto;
import com.example.seanceserver.dto.SeanceDto;
import com.example.seanceserver.dto.SeanceResponseDto;
import com.example.seanceserver.entities.Seance;
import com.example.seanceserver.repository.SeanceRepository;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

@Service
public class SeanceServiceImpl implements SeanceService {

    private final SeanceRepository seanceRepository;

    public SeanceServiceImpl(SeanceRepository seanceRepository) {
        this.seanceRepository = seanceRepository;
    }

    @Override
    public Seance createSeance(SeanceDto seanceDto,int user_id) {
        Seance seance = new Seance();
        seance.setTitle(seanceDto.getTitle());
        seance.setStartDate(seanceDto.getStartDate());
        seance.setEndDate(seanceDto.getEndDate());
        seance.setCoachId(seanceDto.getCoachId());
        return seanceRepository.save(seance);
    }

    @Override
    public Seance updateSeance(Long id, SeanceDto seanceDto,int user_id) {
        Seance existing = seanceRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Seance not found"));

        existing.setTitle(seanceDto.getTitle());
        existing.setStartDate(seanceDto.getStartDate());
        existing.setEndDate(seanceDto.getEndDate());
        existing.setCoachId(seanceDto.getCoachId());
        return seanceRepository.save(existing);
    }

    @Override
    public void deleteSeance(Long id) {
        if (!seanceRepository.existsById(id)) {
            throw new RuntimeException("Seance not found");
        }
        seanceRepository.deleteById(id);
    }

    @Override
    public Seance getSeanceById(Long id) {
        return seanceRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Seance not found"));
    }

    @Override
    public List<Seance> getAllSeances() {
        return seanceRepository.findAll();
    }
    @Override
    public Seance addMemberToSeance(Long seanceId, Long memberId) {
        Seance seance = seanceRepository.findById(seanceId)
                .orElseThrow(() -> new RuntimeException("Seance not found"));

        if (seance.getMemberIds() == null) {
            seance.setMemberIds(new HashSet<>()); // ou new HashSet<>()
        }
        seance.getMemberIds().add(memberId);


        return seanceRepository.save(seance);
    }

    @Override
    public Seance removeMemberFromSeance(Long seanceId, Long memberId) {
        Seance seance = seanceRepository.findById(seanceId)
                .orElseThrow(() -> new RuntimeException("Seance not found"));

        if (seance.getMemberIds() != null) {
            seance.getMemberIds().remove(memberId);
        }

        return seanceRepository.save(seance);
    }

    @Override
    public List<Seance> getMemberSeances(long member_id) {

        return seanceRepository.findByMemberId(member_id);
    }
    @Override
    public List<SeanceResponseDto> getSeancesForCoach(Long coachId) {
        List<Seance> seances = seanceRepository.findByCoachId(coachId);

        return seances.stream()
                .map(seance -> {


                    // Retourner le DTO final pour cette séance
                    return new SeanceResponseDto(
                            seance.getId(),
                            seance.getTitle(),
                            seance.getStartDate(),
                            seance.getEndDate()
                    );
                })
                .toList();
    }

}