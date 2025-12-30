package com.example.seanceserver.services;

import com.example.seanceserver.dto.SeanceDto;
import com.example.seanceserver.dto.SeanceResponseDto;
import com.example.seanceserver.entities.Seance;

import java.util.List;

public interface SeanceService {
    Seance createSeance(SeanceDto seanceDto,int user_id);

    Seance updateSeance(Long id, SeanceDto seanceDto,int user_id);

    void deleteSeance(Long id);

    Seance getSeanceById(Long id);

    List<Seance> getAllSeances();
    Seance addMemberToSeance(Long seanceId, Long memberId);

    Seance removeMemberFromSeance(Long seanceId, Long memberId);

    List<Seance> getMemberSeances(long member_id);
    List<SeanceResponseDto> getSeancesForCoach(Long coachId);

}
