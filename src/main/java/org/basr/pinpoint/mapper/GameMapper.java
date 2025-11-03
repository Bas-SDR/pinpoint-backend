package org.basr.pinpoint.mapper;

import org.basr.pinpoint.dto.GameLeagueResponseDto;
import org.basr.pinpoint.dto.GamePlayerResponseDto;
import org.basr.pinpoint.dto.GameTeamResponseDto;
import org.basr.pinpoint.model.Game;

public class GameMapper {

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
