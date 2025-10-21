package org.basr.pinpoint.controller;

import org.basr.pinpoint.dto.LeagueResponseDto;
import org.basr.pinpoint.mapper.LeagueMapper;
import org.basr.pinpoint.service.LeagueService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/leagues")
public class LeagueController {

    private final LeagueService service;

    public LeagueController(LeagueService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<LeagueResponseDto>> getAllLeagues(){
        return ResponseEntity.ok(LeagueMapper.toResponseDtoList(this.service.getAllLeagues()));
    }
}
