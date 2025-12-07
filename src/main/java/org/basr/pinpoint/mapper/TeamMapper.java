package org.basr.pinpoint.mapper;

import org.basr.pinpoint.dto.TeamCreateDto;
import org.basr.pinpoint.dto.TeamRequestDto;
import org.basr.pinpoint.dto.TeamResponseDto;
import org.basr.pinpoint.model.Team;

import java.util.List;
import java.util.stream.Collectors;

public class TeamMapper {
    public static Team toEntity(TeamRequestDto teamRequestDto) {
        Team team = new Team(
                teamRequestDto.getTeamName());
        return team;
    }

    public static Team toCreateEntity(TeamCreateDto teamCreateDto) {
        return new Team(teamCreateDto.getTeamName());
    }

    public static TeamResponseDto toResponseDto(Team team) {
        TeamResponseDto teamResponseDto = new TeamResponseDto();
        teamResponseDto.setId(team.getId());
        teamResponseDto.setTeamName(team.getTeamName());
        teamResponseDto.setTeamPic(team.getTeamPic());

        if (team.getCaptain() != null) {
            teamResponseDto.setCaptainId(team.getCaptain().getId());
            teamResponseDto.setCaptainFirstName(team.getCaptain().getFirstName());
            teamResponseDto.setCaptainLastName(team.getCaptain().getLastName());
        }
        return teamResponseDto;
    }

    public static List<TeamResponseDto> toResponseDtoList(List<Team> teams) {
        return teams.stream().map(TeamMapper::toResponseDto).collect(Collectors.toList());
    }

    public static void updateEntity(Team team, TeamRequestDto teamRequestDto) {
        team.setTeamName(teamRequestDto.getTeamName());
    }
}
