package org.basr.pinpoint.controller;

import jakarta.validation.Valid;
import org.basr.pinpoint.dto.*;
import org.basr.pinpoint.helper.UriHelper;
import org.basr.pinpoint.mapper.LeagueMapper;
import org.basr.pinpoint.model.League;
import org.basr.pinpoint.model.Player;
import org.basr.pinpoint.service.LeagueService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.HashSet;
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

    @GetMapping("/{id}/teams")
    public ResponseEntity<List<LeagueTeamInfoDto>> getTeamsByLeague(@PathVariable Long id) {
        return ResponseEntity.ok(service.getTeamsByLeagueId(id));
    }

    @GetMapping("/{id}/players")
    public ResponseEntity<List<LeaguePlayerInfoDto>> getPlayersInLeagueById(@PathVariable Long id) {
        List<Player> players = service.getPlayersInLeagueById(id);
        List<LeaguePlayerInfoDto> playerDtoList = LeagueMapper.toLeaguePlayerInfoDtoList(new HashSet<>(players));

        return ResponseEntity.ok(playerDtoList);
    }

    @PostMapping
    public ResponseEntity<LeagueResponseDto> createLeague(@Valid @RequestBody LeagueRequestDto leagueRequestDto){

        League league = this.service.createLeague(leagueRequestDto);
        LeagueResponseDto leagueResponseDto = LeagueMapper.toResponseDto(league);

        URI location = UriHelper.buildUri(league.getId());

        return ResponseEntity.created(location).body(leagueResponseDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteLeagueById(@PathVariable Long id) {
        var result = service.deleteLeagueById(id);
        if (result) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<LeagueResponseDto> updateLeagueById(@Valid @PathVariable Long id, @RequestBody LeagueRequestDto leagueRequestDto){
        League league = service.updateLeagueById(id, leagueRequestDto);

        return ResponseEntity.ok(LeagueMapper.toResponseDto(league));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<LeagueResponseDto> patchLeagueById(@Valid @PathVariable Long id, @RequestBody LeaguePatchDto leaguePatchDto){
        League league = service.patchLeagueById(id, leaguePatchDto);

        return ResponseEntity.ok(LeagueMapper.toResponseDto(league));
    }
}
