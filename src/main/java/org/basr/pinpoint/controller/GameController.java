package org.basr.pinpoint.controller;

import org.basr.pinpoint.mapper.GameMapper;
import org.basr.pinpoint.model.Game;
import org.basr.pinpoint.service.GameService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/games")
public class GameController {

    private final GameService service;

    public GameController(GameService service) {
        this.service = service;
    }

//    @GetMapping
//    public ResponseEntity<Game> getGameById(@RequestParam long id) {
//        return ResponseEntity.ok(GameMapper.toGameBaseDto(this.service.getGameById(id)));
//    }
}
