package com.example.seanceserver.rest;

import com.example.seanceserver.dto.SeanceDto;
import com.example.seanceserver.dto.SeanceResponseDto;
import com.example.seanceserver.entities.Seance;
import com.example.seanceserver.services.SeanceService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/seances")
public class SeanceController {

    private final SeanceService seanceService;

    public SeanceController(SeanceService seanceService) {
        this.seanceService = seanceService;
    }

    // GET all seances
    @GetMapping
    public ResponseEntity<List<Seance>> getAllSeances() {
        return ResponseEntity.ok(seanceService.getAllSeances());
    }

    // GET seance by id
    @GetMapping("/{id}")
    public ResponseEntity<Seance> getSeanceById(@PathVariable Long id) {
        return ResponseEntity.ok(seanceService.getSeanceById(id));
    }

    // CREATE seance
    @PostMapping
    public ResponseEntity<Seance> createSeance(@RequestBody SeanceDto seanceDto,@RequestHeader("x-user-id") int userId) {
        Seance created = seanceService.createSeance(seanceDto,userId);
        return ResponseEntity.ok(created);
    }

    // UPDATE seance
    @PutMapping("/{id}")
    public ResponseEntity<Seance> updateSeance(@PathVariable Long id,
                                               @RequestBody SeanceDto seanceDto,@RequestHeader("x-user-id") int userId) {
        Seance updated = seanceService.updateSeance(id, seanceDto,userId);
        return ResponseEntity.ok(updated);
    }

    // DELETE seance
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSeance(@PathVariable Long id) {
        seanceService.deleteSeance(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{seanceId}/members/{memberId}")
    public ResponseEntity<Seance> addMemberToSeance(@PathVariable Long seanceId,
                                                    @PathVariable Long memberId) {
        Seance updated = seanceService.addMemberToSeance(seanceId, memberId);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{seanceId}/members/{memberId}")
    public ResponseEntity<Seance> removeMemberFromSeance(@PathVariable Long seanceId,
                                                         @PathVariable Long memberId) {
        Seance updated = seanceService.removeMemberFromSeance(seanceId, memberId);
        return ResponseEntity.ok(updated);
    }
    @GetMapping("/member/{memberId}")
    public ResponseEntity<List<Seance>> getSeancesForMember(@PathVariable Long memberId) {
        return ResponseEntity.ok(seanceService.getMemberSeances(memberId));
    }

    @GetMapping("/coach/{coachId}")
    public ResponseEntity<List<SeanceResponseDto>> getSeancesForCoach(@PathVariable Long coachId) {
        return ResponseEntity.ok(seanceService.getSeancesForCoach(coachId));
    }



}