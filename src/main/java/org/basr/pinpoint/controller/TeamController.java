package org.basr.pinpoint.controller;

import jakarta.validation.Valid;
import org.basr.pinpoint.dto.TeamCreateDto;
import org.basr.pinpoint.dto.TeamPatchDto;
import org.basr.pinpoint.dto.TeamRequestDto;
import org.basr.pinpoint.dto.TeamResponseDto;
import org.basr.pinpoint.helper.UriHelper;
import org.basr.pinpoint.mapper.TeamMapper;
import org.basr.pinpoint.model.Team;
import org.basr.pinpoint.security.MyUserDetails;
import org.basr.pinpoint.service.TeamService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.nio.file.AccessDeniedException;
import java.util.List;

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

    @GetMapping
    public ResponseEntity<List<TeamResponseDto>> getAllTeams() {
        return ResponseEntity.ok(TeamMapper.toResponseDtoList(this.service.getAllTeams()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<TeamResponseDto> getTeamById(@PathVariable Long id) {
        return ResponseEntity.ok(TeamMapper.toResponseDto(this.service.getTeamById(id)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTeamById(@PathVariable Long id) {
        var result = service.deleteTeam(id);
        if (result) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<TeamResponseDto> updateTeam(@PathVariable Long id, @Valid @RequestBody TeamRequestDto teamRequestDto, @AuthenticationPrincipal MyUserDetails principal) throws AccessDeniedException {

        Team team = service.getTeamById(id);

        boolean isCaptain = team.getCaptain() != null && team.getCaptain().getId() == principal.getId();
        boolean isAdmin = principal.getAuthorities().stream()
                .anyMatch(a -> a.getAuthority().equals("ADMIN"));

        if (!isCaptain && !isAdmin) {
            throw new AccessDeniedException("Only the captain or admin can update the team");
        }

        Team updated = service.updateTeam(id, teamRequestDto);
        return ResponseEntity.ok(TeamMapper.toResponseDto(updated));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<TeamResponseDto> updatePartialTeam(@PathVariable Long id, @Valid @RequestBody TeamPatchDto teamPatchDto, @AuthenticationPrincipal MyUserDetails principal) throws AccessDeniedException {

        Team team = service.getTeamById(id);

        boolean isCaptain = team.getCaptain() != null && team.getCaptain().getId() == principal.getId();
        boolean isAdmin = principal.getAuthorities().stream()
                .anyMatch(a -> a.getAuthority().equals("ADMIN"));

        if (!isCaptain && !isAdmin) {
            throw new AccessDeniedException("Only the captain or admin can update the team");
        }

        TeamResponseDto teamResponseDto = TeamMapper.toResponseDto(service.patchTeam(id, teamPatchDto));

        return ResponseEntity.ok(teamResponseDto);
    }
}
