package org.basr.pinpoint.service;

import org.basr.pinpoint.exception.ResourceNotFoundException;
import org.basr.pinpoint.model.Game;
import org.basr.pinpoint.repository.GameRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GameService {

    private final GameRepository repos;

    public GameService(GameRepository repos) {
        this.repos = repos;
    }

    public Game getGameById(long id) {
        return this.repos.findById(id).orElseThrow(() -> new ResourceNotFoundException("Game " + id + " not found"));
    }

    public List<Game> getGamesByPlayer(long playerId) {
        return this.repos.getGamesByPlayerId(playerId);
    }

    public List<Game> getGamesByTeam(long teamId) {
        return this.repos.getGamesByTeamId(teamId);
    }

    public List<Game> getGamesByLeague(long leagueId) {
        return this.repos.getGamesByLeagueId(leagueId);
    }
}
