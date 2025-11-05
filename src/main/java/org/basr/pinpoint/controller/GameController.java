package org.basr.pinpoint.controller;

import jakarta.validation.Valid;
import org.basr.pinpoint.dto.*;
import org.basr.pinpoint.helper.UriHelper;
import org.basr.pinpoint.mapper.GameMapper;
import org.basr.pinpoint.model.Game;
import org.basr.pinpoint.service.GameService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

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

    @PostMapping
    public ResponseEntity<GameFullResponseDto> createGame(@Valid @RequestBody GameRequestDto gameRequestDto) {

        Game game = this.service.createGame(gameRequestDto);
        GameFullResponseDto gameFullResponseDto = GameMapper.toGameFullResponseDto(game);

        URI location = UriHelper.buildUri(game.getId());

        return ResponseEntity.created(location).body(gameFullResponseDto);
    }

    @PostMapping("/batch")
    public ResponseEntity<List<GameFullResponseDto>> createMultipleGames(@Valid @RequestBody List<GameRequestDto> gameRequestDtos) {

        List<Game> games = this.service.createMultipleGames(gameRequestDtos);
        List<GameFullResponseDto> gameFullResponseDtos = games.stream()
                .map(GameMapper::toGameFullResponseDto)
                .collect(Collectors.toList());

        return ResponseEntity.status(HttpStatus.CREATED).body(gameFullResponseDtos);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteGameById(@PathVariable Long id) {
        var result = service.deleteGameById(id);
        if (result) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<GameFullResponseDto> updateGameById(@Valid @PathVariable Long id, @RequestBody GameRequestDto gameRequestDto) {
        Game game = service.updateGameById(id, gameRequestDto);
        return ResponseEntity.ok(GameMapper.toGameFullResponseDto(game));
    }
}
