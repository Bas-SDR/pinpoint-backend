package org.basr.pinpoint.service;

import org.basr.pinpoint.dto.TeamCreateDto;
import org.basr.pinpoint.dto.TeamRequestDto;
import org.basr.pinpoint.exception.ResourceNotFoundException;
import org.basr.pinpoint.mapper.TeamMapper;
import org.basr.pinpoint.model.Team;
import org.basr.pinpoint.model.User;
import org.basr.pinpoint.repository.TeamRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TeamService {

    private final TeamRepository repos;
    private final RoleService roleService;
    private final UserService userService;

    public TeamService(TeamRepository repos, RoleService roleService, UserService userService) {
        this.repos = repos;
        this.roleService = roleService;
        this.userService = userService;
    }

    public Team createTeam(TeamCreateDto teamCreateDto) {
        Team team = TeamMapper.toCreateEntity(teamCreateDto);
        if (teamCreateDto.getCaptainId() != null) {
            User captain = userService.getSingleUser(teamCreateDto.getCaptainId());
            team.setCaptain(captain);
            roleService.assignTeamCaptain(captain);
        }

        return repos.save(team);
    }

    public List<Team> getAllTeams() {
        return this.repos.findAll();
    }

    public Team getTeamById(Long id) {
        return this.repos.findById(id).orElseThrow(()-> new ResourceNotFoundException("Team " + id + " not found"));
    }

    public boolean deleteTeam(Long id) {
        if (repos.existsById(id)) {
            repos.deleteById(id);
            return true;
        } else {
            return false;
        }
    }

    public Team updateTeam(Long id, TeamRequestDto teamRequestDto) {
        Team team = repos.findById(id).orElseThrow(()-> new ResourceNotFoundException("Team " + id + " not found"));

        TeamMapper.updateEntity(team, teamRequestDto);

        if (teamRequestDto.getCaptainId() != null) {
            User captain = userService.getSingleUser(teamRequestDto.getCaptainId());
            team.setCaptain(captain);
        }
        return repos.save(team);
    }
}
