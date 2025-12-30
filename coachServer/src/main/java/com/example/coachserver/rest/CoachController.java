package com.example.coachserver.rest;


import com.example.coachserver.dto.CoachDto;
import com.example.coachserver.dto.CoachResponseDto;
import com.example.coachserver.dto.SeanceDto;
import com.example.coachserver.entities.Coach;
import com.example.coachserver.services.CoachService;
import com.example.coachserver.services.clients.SeanceClient;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/coaches")
@AllArgsConstructor
public class CoachController {
    private final CoachService coachService;
    private SeanceClient seanceClient;

    // GET all coaches
    @GetMapping
    public ResponseEntity<List<CoachResponseDto>> getAllCoaches() {
        return ResponseEntity.ok(coachService.getAllCoaches());
    }

    // GET coach by id
    @GetMapping("/{id}")
    public ResponseEntity<CoachResponseDto> getCoachById(@PathVariable Long id) {
        return ResponseEntity.ok(coachService.getCoachById(id));
    }

    @GetMapping("/{id}/seances")
    public ResponseEntity<List<SeanceDto>> getCoachSeances(@PathVariable Long id) {
        return seanceClient.getSeancesForCoach(id);
    }

    // CREATE coach
    @PostMapping
    public ResponseEntity<Coach> createCoach(@RequestBody CoachDto coach, @RequestHeader("x-user-id") int userId) {
        Coach created = coachService.createCoach(coach,userId);
        return ResponseEntity.ok(created);
    }

    // UPDATE coach
    @PutMapping("/{id}")
    public ResponseEntity<Coach> updateCoach(@PathVariable Long id,
                                             @RequestBody CoachDto coachdto,@RequestHeader("x-user-id") int userId) {
        Coach updated = coachService.updateCoach(id, coachdto,userId);
        return ResponseEntity.ok(updated);
    }

    // DELETE coach
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCoach(@PathVariable Long id) {
        coachService.deleteCoach(id);
        return ResponseEntity.noContent().build();
    }
}
