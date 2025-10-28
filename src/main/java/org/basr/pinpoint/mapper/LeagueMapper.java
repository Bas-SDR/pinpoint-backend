package org.basr.pinpoint.mapper;

import org.basr.pinpoint.dto.LeaguePlayerInfoDto;
import org.basr.pinpoint.dto.LeagueRequestDto;
import org.basr.pinpoint.dto.LeagueResponseDto;
import org.basr.pinpoint.dto.LeagueTeamInfoDto;
import org.basr.pinpoint.model.League;
import org.basr.pinpoint.model.Player;
import org.basr.pinpoint.model.Team;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class LeagueMapper {

    public static LeagueResponseDto toResponseDto(League league) {
        LeagueResponseDto leagueResponseDto = new LeagueResponseDto();
        leagueResponseDto.setId(league.getId());
        leagueResponseDto.setLeagueName(league.getLeagueName());
        leagueResponseDto.setLeagueDivision(league.getLeagueDivision());
        leagueResponseDto.setLeagueDay(league.getLeagueDay());
        leagueResponseDto.setCreationDate(league.getCreationDate());
        leagueResponseDto.setLeagueTeams(toTeamDtoList(league.getTeams()));

        return leagueResponseDto;
    }

    public static List<LeagueResponseDto> toResponseDtoList(List<League> leagues) {
        return leagues.stream().map(LeagueMapper::toResponseDto).collect(Collectors.toList());
    }

    public static List<LeagueTeamInfoDto> toTeamDtoList(Set<Team> teams) {
        return teams.stream().map(team -> new LeagueTeamInfoDto(
                        team.getId(),
                        team.getTeamName()
                ))
                .collect(Collectors.toList());
    }

    public static League toEntity(LeagueRequestDto leagueRequestDto) {
        return new League(
                leagueRequestDto.getLeagueName(),
                leagueRequestDto.getLeagueDivision(),
                leagueRequestDto.getLeagueDay()
        );
    }

    public static void updateEntity(League league, LeagueRequestDto dto) {
        league.setLeagueName(dto.getLeagueName());
        league.setLeagueDivision(dto.getLeagueDivision());
        league.setLeagueDay(dto.getLeagueDay());
    }

    public static List<LeaguePlayerInfoDto> toLeaguePlayerInfoDtoList(Set<Player> players) {
        return players.stream()
                .map(player -> new LeaguePlayerInfoDto(
                        player.getId(),
                        player.getUser().getFirstName(),
                        player.getUser().getLastName()
                ))
                .collect(Collectors.toList());
    }

}
