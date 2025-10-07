package org.basr.pinpoint.mapper;

import org.basr.pinpoint.dto.TeamCreateDto;
import org.basr.pinpoint.dto.TeamRequestDto;
import org.basr.pinpoint.dto.TeamResponseDto;
import org.basr.pinpoint.model.Team;

public class TeamMapper {
    public static Team toEntity(TeamRequestDto teamRequestDto) {
        Team team = new Team(
                teamRequestDto.getTeamName(),
                teamRequestDto.getTeamPic());
        return team;
    }

    public static Team toCreateEntity(TeamCreateDto dto) {
        return new Team(dto.getTeamName());
    }

    public static TeamResponseDto toResponseDto(Team team) {
        TeamResponseDto teamResponseDto = new TeamResponseDto();
        teamResponseDto.setId(team.getId());
        teamResponseDto.setTeamName(team.getTeamName());
        teamResponseDto.setCaptainId(team.getCaptain() != null ? team.getCaptain().getId() : 0L);
        return teamResponseDto;
    }
}
