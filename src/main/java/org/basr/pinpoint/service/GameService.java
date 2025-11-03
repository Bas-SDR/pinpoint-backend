package org.basr.pinpoint.service;

import org.basr.pinpoint.exception.ResourceNotFoundException;
import org.basr.pinpoint.model.Game;
import org.basr.pinpoint.repository.GameRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

@Service
public class GameService {

    private final GameRepository repos;

    public GameService(GameRepository repos) {
        this.repos = repos;
    }

    public Game getGameById(@RequestParam long id) {
        return this.repos.findById(id).orElseThrow(() -> new ResourceNotFoundException("Game " + id + " not found"));
    }
}
