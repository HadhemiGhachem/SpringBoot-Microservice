package com.example.coachserver.services.clients;


import com.example.coachserver.dto.SeanceDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "seanceserver")

public interface SeanceClient {

    @GetMapping("/api/seances/coach/{coachId}")
    public ResponseEntity<List<SeanceDto>> getSeancesForCoach(@PathVariable Long coachId);
}
