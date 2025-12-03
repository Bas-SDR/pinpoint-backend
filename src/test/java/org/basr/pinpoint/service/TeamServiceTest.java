package org.basr.pinpoint.service;

import org.basr.pinpoint.dto.TeamCreateDto;
import org.basr.pinpoint.model.Team;
import org.basr.pinpoint.model.User;
import org.basr.pinpoint.repository.TeamRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class TeamServiceTest {

    //Assert
    Team team1;
    Team team2;

    @Mock
    TeamRepository teamRepos;

    @Mock
    RoleService roleService;

    @Mock
    UserService userService;

    @InjectMocks
    TeamService teamService;

    @BeforeEach
    void setUp() {
        team1 = new Team();
        team1.setId(1L);
        team1.setTeamName("team1");
        team1.setCreationDate(LocalDate.of(2022,11,5));

        team2 = new Team();
        team2.setId(2L);
        team2.setTeamName("team2");
        team2.setCreationDate(LocalDate.of(2021,6,6));

    }

    @Test
    void shouldCreateTeamWithCaptain() {
        //Arrange
        TeamCreateDto teamCreateDto = new TeamCreateDto();
        teamCreateDto.setTeamName("team3");
        teamCreateDto.setCaptainId(3L);

        User captain = new User();
        ReflectionTestUtils.setField(captain, "id", 3L);
        captain.setFirstName("Captain");
        captain.setLastName("Leader");

        when(userService.getSingleUser(3L)).thenReturn(captain);

        Team savedTeam = new Team();
        savedTeam.setId(3L);
        savedTeam.setTeamName(teamCreateDto.getTeamName());
        savedTeam.setCaptain(captain);

        when(teamRepos.save(Mockito.any(Team.class))).thenReturn(savedTeam);
        //Act
        Team team = teamService.createTeam(teamCreateDto);
        //Assert
        assertNotNull(team);
        assertEquals("team3", team.getTeamName());
        assertEquals(3L, team.getCaptain().getId());
        assertEquals("Captain", team.getCaptain().getFirstName());
        assertEquals("Leader", team.getCaptain().getLastName());
    }

    @Test
    void shouldCreateTeamWithoutCaptain() {
        //Arrange
        TeamCreateDto teamCreateDto = new TeamCreateDto();
        teamCreateDto.setTeamName("team3");

        Team savedTeam = new Team();
        savedTeam.setId(3L);
        savedTeam.setTeamName(teamCreateDto.getTeamName());

        when(teamRepos.save(Mockito.any(Team.class))).thenReturn(savedTeam);
        //Act
        Team team = teamService.createTeam(teamCreateDto);
        //Assert
        assertNotNull(team);
        assertEquals("team3", team.getTeamName());
        assertNull(team.getCaptain());
    }

    @Test
    void shouldGetAllTeams() {
        //Arrange
        when(teamRepos.findAll()).thenReturn(Arrays.asList(team1, team2));
        //Act
        List<Team> teams = teamService.getAllTeams();
        //Assert
        assertNotNull(teams);
        assertEquals(2, teams.size());
        assertEquals(team1, teams.get(0));
        assertEquals(team2, teams.get(1));
    }

    @Test
    void shouldGetTeamById() {
        //Arrange
        when(teamRepos.findById(1L)).thenReturn(Optional.of(team1));
        //Act
        Team team = teamService.getTeamById(1L);
        //Assert
        assertEquals(1L, team.getId());

    }

    @Test
    void deleteTeam() {
        //Arrange

        //Act

        //Assert

    }

    @Test
    void updateTeam() {
        //Arrange

        //Act

        //Assert

    }

    @Test
    void patchTeam() {
        //Arrange

        //Act

        //Assert

    }
}