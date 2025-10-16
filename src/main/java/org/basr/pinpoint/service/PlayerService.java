package org.basr.pinpoint.service;

import org.basr.pinpoint.dto.PlayerResponseDto;
import org.basr.pinpoint.exception.ResourceNotFoundException;
import org.basr.pinpoint.mapper.PlayerMapper;
import org.basr.pinpoint.model.Player;
import org.basr.pinpoint.repository.PlayerRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlayerService {

    private final PlayerRepository repos;

    public PlayerService(PlayerRepository repos) {
        this.repos = repos;
    }

    public Player getPlayerById(Long id) {
        return this.repos.findById(id).orElseThrow(() -> new ResourceNotFoundException("Player " + id + " not found"));
    }

    public List<Player> getAllPlayers() {
        return this.repos.findAll();
    }

    public List<PlayerResponseDto> getTop10HighestGame() {
        List<Player> players = repos.findTop10ByOrderByStatsHighestGameDesc();
        return PlayerMapper.toResponseDtoList(players);
    }

    public List<PlayerResponseDto> getTop10HighestSeries() {
        List<Player> players = repos.findTop10ByOrderByStatsHighestSeriesDesc();
        return PlayerMapper.toResponseDtoList(players);
    }
}
