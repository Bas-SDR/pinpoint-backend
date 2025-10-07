package org.basr.pinpoint.service;

import org.basr.pinpoint.dto.TeamCreateDto;
import org.basr.pinpoint.mapper.TeamMapper;
import org.basr.pinpoint.model.Team;
import org.basr.pinpoint.model.User;
import org.basr.pinpoint.repository.TeamRepository;
import org.springframework.stereotype.Service;

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
}
