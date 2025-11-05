package org.basr.pinpoint.service;

import org.basr.pinpoint.dto.GameRequestDto;
import org.basr.pinpoint.exception.ResourceNotFoundException;
import org.basr.pinpoint.mapper.GameMapper;
import org.basr.pinpoint.model.Game;
import org.basr.pinpoint.model.League;
import org.basr.pinpoint.model.Player;
import org.basr.pinpoint.model.Team;
import org.basr.pinpoint.repository.GameRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class GameService {

    private final GameRepository repos;
    private final PlayerService playerService;
    private final TeamService teamService;
    private final LeagueService leagueService;

    public GameService(GameRepository repos, PlayerService playerService, TeamService teamService,  LeagueService leagueService) {
        this.repos = repos;
        this.playerService = playerService;
        this.teamService = teamService;
        this.leagueService = leagueService;
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

    public Game createGame(GameRequestDto gameRequestDto) {
        Player player = playerService.getPlayerById(gameRequestDto.getPlayerId());
        Team team = teamService.getTeamById(gameRequestDto.getTeamId());
        League league = leagueService.getLeagueById(gameRequestDto.getLeagueId());

        Game game = GameMapper.toEntity(gameRequestDto, player, team, league);
        return this.repos.save(game);
    }

    public List<Game> createMultipleGames(List<GameRequestDto> gameRequestDtos) {
        return gameRequestDtos
                .stream()
                .map(this::createGame)
                .collect(Collectors.toList());
    }

    public boolean deleteGameById(long id) {
        if (repos.existsById(id)) {
            this.repos.deleteById(id);
            return true;
        } else
            return false;
    }

    public Game updateGameById(long id, GameRequestDto gameRequestDto) {
        Game game = repos.findById(id).orElseThrow(() -> new ResourceNotFoundException("Game " + id + " not found"));
        Player player = playerService.getPlayerById(gameRequestDto.getPlayerId());
        Team team = teamService.getTeamById(gameRequestDto.getTeamId());
        League league = leagueService.getLeagueById(gameRequestDto.getLeagueId());
        GameMapper.updateEntity(game, gameRequestDto, player, team, league);

        return repos.save(game);
    }
}
