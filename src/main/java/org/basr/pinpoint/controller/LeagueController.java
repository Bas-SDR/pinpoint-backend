package org.basr.pinpoint.controller;

import org.basr.pinpoint.dto.LeagueRequestDto;
import org.basr.pinpoint.dto.LeagueResponseDto;
import org.basr.pinpoint.helper.UriHelper;
import org.basr.pinpoint.mapper.LeagueMapper;
import org.basr.pinpoint.model.League;
import org.basr.pinpoint.service.LeagueService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
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

    @GetMapping("/{id}")
    public ResponseEntity<LeagueResponseDto> getLeagueById(@PathVariable Long id){
        return ResponseEntity.ok(LeagueMapper.toResponseDto(this.service.getLeagueById(id)));
    }

    @GetMapping(params = "name")
    public ResponseEntity<LeagueResponseDto> getLeagueByName(@RequestParam String name){
        League league = service.getLeagueByName(name);
        return ResponseEntity.ok(LeagueMapper.toResponseDto(league));
    }

    @PostMapping
    public ResponseEntity<LeagueResponseDto> createLeague(@RequestBody LeagueRequestDto leagueRequestDto){

        League league = this.service.createLeague(leagueRequestDto);
        LeagueResponseDto leagueResponseDto = LeagueMapper.toResponseDto(league);

        URI location = UriHelper.buildUri(league.getId());

        return ResponseEntity.created(location).body(leagueResponseDto);
    }
}
