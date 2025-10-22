package org.basr.pinpoint.mapper;

import org.basr.pinpoint.dto.LeagueRequestDto;
import org.basr.pinpoint.dto.LeagueResponseDto;
import org.basr.pinpoint.dto.LeagueTeamInfoDto;
import org.basr.pinpoint.model.League;
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

    public static League toCreateEntity(LeagueRequestDto leagueRequestDto) {
        return new League(
                leagueRequestDto.getLeagueName(),
                leagueRequestDto.getLeagueDivision(),
                leagueRequestDto.getLeagueDay()
        );
    }

}
