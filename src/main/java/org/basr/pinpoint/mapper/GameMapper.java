package org.basr.pinpoint.mapper;

import org.basr.pinpoint.dto.*;
import org.basr.pinpoint.model.Game;

public class GameMapper {

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

    public static GameTeamResponseDto toTeamResponseDto(Game game) {
        GameTeamResponseDto gameTeamResponseDto = new GameTeamResponseDto();
        gameTeamResponseDto.setTeamId(game.getTeam().getId());
        gameTeamResponseDto.setId(game.getId());
        gameTeamResponseDto.setDatePlayed(game.getDatePlayed());
        gameTeamResponseDto.setPinfall(game.getPinfall());

        return gameTeamResponseDto;
    }

    public static GamePlayerResponseDto toPlayerResponseDto(Game game) {
        GamePlayerResponseDto gamePlayerResponseDto = new GamePlayerResponseDto();
        gamePlayerResponseDto.setPlayerId(game.getLeague().getId());
        gamePlayerResponseDto.setId(game.getId());
        gamePlayerResponseDto.setDatePlayed(game.getDatePlayed());
        gamePlayerResponseDto.setPinfall(game.getPinfall());

        return gamePlayerResponseDto;
    }
}
