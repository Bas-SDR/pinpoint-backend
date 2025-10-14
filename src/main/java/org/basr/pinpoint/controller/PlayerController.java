package org.basr.pinpoint.controller;

import org.basr.pinpoint.dto.PlayerResponseDto;
import org.basr.pinpoint.mapper.PlayerMapper;
import org.basr.pinpoint.service.PlayerService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/players")
public class PlayerController {

    private final PlayerService service;

    public PlayerController(PlayerService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<PlayerResponseDto>> findAllPlayers() {
        return ResponseEntity.ok(PlayerMapper.toResponseDtoList(this.service.getAllPlayers()));
    }
}
