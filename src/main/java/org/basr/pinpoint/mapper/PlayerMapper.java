package org.basr.pinpoint.mapper;

import org.basr.pinpoint.dto.PlayerResponseDto;
import org.basr.pinpoint.dto.PlayerStatsDto;
import org.basr.pinpoint.dto.PlayerTeamInfoDto;
import org.basr.pinpoint.model.Player;
import org.basr.pinpoint.model.Stats;
import org.basr.pinpoint.model.Team;

import java.util.List;
import java.util.stream.Collectors;

public class PlayerMapper {

    public static PlayerResponseDto toResponseDto(Player player) {
        PlayerResponseDto playerResponseDto = new PlayerResponseDto();
        playerResponseDto.setId(player.getUser().getId());
        playerResponseDto.setFirstName(player.getUser().getFirstName());
        playerResponseDto.setLastName(player.getUser().getLastName());
        playerResponseDto.setStats(toPlayerStatsDto(player.getStats()));
        playerResponseDto.setTeams(toTeamDtoList(player.getTeams()));

        return playerResponseDto;
    }

    public static List<PlayerResponseDto> toResponseDtoList(List<Player> players) {
        return players.stream().map(PlayerMapper::toResponseDto).collect(Collectors.toList());
    }

    private static List<PlayerTeamInfoDto> toTeamDtoList(List<Team> teams) {
        return teams.stream().map(team -> new PlayerTeamInfoDto(
                        team.getId(),
                        team.getTeamName(),
                        team.getTeamPic()
                ))
                .collect(Collectors.toList());
    }

    private static PlayerStatsDto toPlayerStatsDto(Stats stats) {
        PlayerStatsDto playerStatsDto = new PlayerStatsDto();
        playerStatsDto.setGamesPlayed(stats.getGamesPlayed());
        playerStatsDto.setHighestGame(stats.getHighestGame());
        playerStatsDto.setHighestSeries(stats.getHighestSeries());
        playerStatsDto.setTotalPinfall(stats.getTotalPinfall());
        playerStatsDto.setPerfectGames(stats.getPerfectGames());
        playerStatsDto.setAverageScore(stats.getAverageScore());
        return playerStatsDto;
    }
}
