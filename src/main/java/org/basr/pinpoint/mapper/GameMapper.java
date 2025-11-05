package org.basr.pinpoint.mapper;

import org.basr.pinpoint.dto.*;
import org.basr.pinpoint.model.Game;
import org.basr.pinpoint.model.League;
import org.basr.pinpoint.model.Player;
import org.basr.pinpoint.model.Team;

import java.util.List;
import java.util.stream.Collectors;

public class GameMapper {

    public static Game toEntity(GameRequestDto gameRequestDto, Player player, Team team, League league) {
        Game game = new Game();
                game.setDatePlayed(gameRequestDto.getDatePlayed());
                game.setPinfall(gameRequestDto.getPinfall());
                game.setGameNumber(gameRequestDto.getGameNumber());
                game.setPlayer(player);
                game.setTeam(team);
                game.setLeague(league);
        return game;
    }

    public static GameFullResponseDto toGameFullResponseDto(Game game) {
        GameFullResponseDto gameFullResponseDto = new GameFullResponseDto();
        gameFullResponseDto.setId(game.getId());
        gameFullResponseDto.setDatePlayed(game.getDatePlayed());
        gameFullResponseDto.setPinfall(game.getPinfall());
        gameFullResponseDto.setLeagueId(game.getLeague().getId());
        gameFullResponseDto.setTeamId(game.getTeam().getId());
        gameFullResponseDto.setPlayerId(game.getLeague().getId());

        return gameFullResponseDto;
    }

    public static GameLeagueResponseDto toLeagueResponseDto(Game game) {
        GameLeagueResponseDto gameLeagueResponseDto = new GameLeagueResponseDto();
        gameLeagueResponseDto.setLeagueId(game.getLeague().getId());
        gameLeagueResponseDto.setId(game.getId());
        gameLeagueResponseDto.setDatePlayed(game.getDatePlayed());
        gameLeagueResponseDto.setPinfall(game.getPinfall());

        return gameLeagueResponseDto;
    }

    public static List<GameLeagueResponseDto> toGameLeagueResponseDtoList(List<Game> games) {
        return games.stream().map(GameMapper::toLeagueResponseDto).collect(Collectors.toList());
    }

    public static GameTeamResponseDto toTeamResponseDto(Game game) {
        GameTeamResponseDto gameTeamResponseDto = new GameTeamResponseDto();
        gameTeamResponseDto.setTeamId(game.getTeam().getId());
        gameTeamResponseDto.setId(game.getId());
        gameTeamResponseDto.setDatePlayed(game.getDatePlayed());
        gameTeamResponseDto.setPinfall(game.getPinfall());

        return gameTeamResponseDto;
    }

    public static List<GameTeamResponseDto> toGameTeamResponseDtoList(List<Game> games) {
        return games.stream().map(GameMapper::toTeamResponseDto).collect(Collectors.toList());
    }

    public static GamePlayerResponseDto toPlayerResponseDto(Game game) {
        GamePlayerResponseDto gamePlayerResponseDto = new GamePlayerResponseDto();
        gamePlayerResponseDto.setPlayerId(game.getLeague().getId());
        gamePlayerResponseDto.setId(game.getId());
        gamePlayerResponseDto.setDatePlayed(game.getDatePlayed());
        gamePlayerResponseDto.setPinfall(game.getPinfall());
        gamePlayerResponseDto.setGameNumber(game.getGameNumber());

        return gamePlayerResponseDto;
    }

    public static List<GamePlayerResponseDto> toGamePlayerResponseDtoList(List<Game> games) {
        return games.stream().map(GameMapper::toPlayerResponseDto).collect(Collectors.toList());
    }

    public static void updateEntity(Game game, GameRequestDto gameRequestDto, Player player, Team team, League league) {
        game.setDatePlayed(gameRequestDto.getDatePlayed());
        game.setPinfall(gameRequestDto.getPinfall());
        game.setGameNumber(gameRequestDto.getGameNumber());
        game.setPlayer(player);
        game.setTeam(team);
        game.setLeague(league);
    }
}

