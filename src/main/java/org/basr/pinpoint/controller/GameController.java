package org.basr.pinpoint.controller;

import org.basr.pinpoint.dto.GameBaseDto;
import org.basr.pinpoint.mapper.GameMapper;
import org.basr.pinpoint.service.GameService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/games")
public class GameController {

    private final GameService service;

    public GameController(GameService service) {
        this.service = service;
    }

    @GetMapping("/{id}")
    public ResponseEntity<GameBaseDto> getGameById(@PathVariable long id) {
        return ResponseEntity.ok(GameMapper.toGameFullResponseDto(this.service.getGameById(id)));
    }
}
