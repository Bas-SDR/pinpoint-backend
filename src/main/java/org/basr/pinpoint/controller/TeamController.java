package org.basr.pinpoint.controller;

import jakarta.validation.Valid;
import org.basr.pinpoint.dto.TeamCreateDto;
import org.basr.pinpoint.dto.TeamRequestDto;
import org.basr.pinpoint.dto.TeamResponseDto;
import org.basr.pinpoint.dto.UserResponseDto;
import org.basr.pinpoint.helper.UriHelper;
import org.basr.pinpoint.mapper.TeamMapper;
import org.basr.pinpoint.mapper.UserMapper;
import org.basr.pinpoint.model.Team;
import org.basr.pinpoint.service.TeamService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@RestController
@RequestMapping("/teams")
public class TeamController {

    private final TeamService service;

    public TeamController(TeamService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<TeamResponseDto> createTeam(@Valid @RequestBody TeamCreateDto teamCreateDto) {

        Team team = this.service.createTeam(teamCreateDto);
        TeamResponseDto teamResponseDto = TeamMapper.toResponseDto(team);

        URI location = UriHelper.buildUri(team.getId());

        return ResponseEntity.created(location).body(teamResponseDto);
    }
}
