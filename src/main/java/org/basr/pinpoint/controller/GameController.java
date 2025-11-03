package org.basr.pinpoint.controller;

import org.basr.pinpoint.dto.GameFullResponseDto;
import org.basr.pinpoint.dto.GameLeagueResponseDto;
import org.basr.pinpoint.dto.GamePlayerResponseDto;
import org.basr.pinpoint.dto.GameTeamResponseDto;
import org.basr.pinpoint.mapper.GameMapper;
import org.basr.pinpoint.service.GameService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/games")
public class GameController {

    private final GameService service;

    public GameController(GameService service) {
        this.service = service;
    }

    @GetMapping("/{id}")
    public ResponseEntity<GameFullResponseDto> getGameById(@PathVariable long id) {
        return ResponseEntity.ok(GameMapper.toGameFullResponseDto(this.service.getGameById(id)));
    }

    @GetMapping("/players/{id}")
    public ResponseEntity<List<GamePlayerResponseDto>> getGameByPlayerId(@PathVariable long id) {
        return ResponseEntity.ok(GameMapper.toGamePlayerResponseDtoList(this.service.getGamesByPlayer(id)));
    }

    @GetMapping("/teams/{id}")
    public ResponseEntity<List<GameTeamResponseDto>> getGameByTeamId(@PathVariable long id) {
        return ResponseEntity.ok(GameMapper.toGameTeamResponseDtoList(this.service.getGamesByTeam(id)));
    }

    @GetMapping("/leagues/{id}")
    public ResponseEntity<List<GameLeagueResponseDto>> getGameByLeagueId(@PathVariable long id) {
        return ResponseEntity.ok(GameMapper.toGameLeagueResponseDtoList(this.service.getGamesByLeague(id)));
    }
}
