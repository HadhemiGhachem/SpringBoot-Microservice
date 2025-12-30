package com.example.seanceserver.repository;

import com.example.seanceserver.entities.Seance;
import feign.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SeanceRepository extends JpaRepository<Seance, Long> {
    @Query("SELECT s FROM Seance s JOIN s.memberIds m WHERE m = :memberId")
    List<Seance> findByMemberId(@Param("memberId") Long memberId);

    List<Seance> findByCoachId(Long coachId);

}
