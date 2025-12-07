package org.basr.pinpoint.service;

import org.basr.pinpoint.dto.TeamCreateDto;
import org.basr.pinpoint.dto.TeamRequestDto;
import org.basr.pinpoint.dto.TeamPatchDto;
import org.basr.pinpoint.exception.ResourceNotFoundException;
import org.basr.pinpoint.mapper.TeamMapper;
import org.basr.pinpoint.model.Team;
import org.basr.pinpoint.model.User;
import org.basr.pinpoint.repository.TeamRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
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

    public Team patchTeam(Long id, TeamPatchDto updatedTeam) {
        Team team = repos.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Team " + id + " not found"));

        if (updatedTeam.getTeamName() != null) {
            team.setTeamName(updatedTeam.getTeamName());
        }

        if (updatedTeam.getTeamPic() != null) {
            team.setTeamPic(updatedTeam.getTeamPic());
        }

        if (updatedTeam.getCaptainId() != null) {
            User captain = userService.getSingleUser(updatedTeam.getCaptainId());
            team.setCaptain(captain);
        }

        return repos.save(team);
    }

    public String uploadTeamPicture(Long teamId, MultipartFile file) {
        Team team = repos.findById(teamId).orElseThrow(() -> new ResourceNotFoundException("Team not found"));

        String imageName = System.currentTimeMillis() + "_" + file.getOriginalFilename();
        Path path = Paths.get("uploads/teampic" + imageName);

        try {
            Files.createDirectories(path.getParent());
            Files.write(path, file.getBytes());
        } catch (IOException e) {
            throw new RuntimeException("Image upload failed");
        }

        String imageUrl = "/images/teampic/" + imageName;
        team.setTeamPic(imageUrl);
        repos.save(team);

        return imageUrl;
    }
}
