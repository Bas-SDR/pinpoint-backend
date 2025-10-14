package org.basr.pinpoint.service;

import org.basr.pinpoint.exception.ResourceNotFoundException;
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

    public Player findById(Long id) {
        return this.repos.findById(id).orElseThrow(() -> new ResourceNotFoundException("Player not found"));
    }

    public List<Player> getAllPlayers() {
        return this.repos.findAll();
    }


}
