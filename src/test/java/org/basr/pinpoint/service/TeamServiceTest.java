package org.basr.pinpoint.service;

import org.basr.pinpoint.dto.TeamCreateDto;
import org.basr.pinpoint.dto.TeamPatchDto;
import org.basr.pinpoint.dto.TeamRequestDto;
import org.basr.pinpoint.exception.ResourceNotFoundException;
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
    void shouldNotGetTeamById() {
        //Arrange
        when(teamRepos.findById(1000L)).thenReturn(Optional.empty());
        //Act
        //Assert
        ResourceNotFoundException ex = assertThrows(ResourceNotFoundException.class, () -> {
            teamService.getTeamById(1000L);
        });
        assertEquals("Team 1000 not found", ex.getMessage());
    }

    @Test
    void shouldDeleteTeam() {
        //Arrange
        when(teamRepos.existsById(1L)).thenReturn(true);
        //Act
        boolean deletedTeam = teamService.deleteTeam(1L);
        //Assert
        assertTrue(deletedTeam);
    }

    @Test
    void shouldNotFindTeamToDelete() {
        //Arrange
        when(teamRepos.existsById(100L)).thenReturn(false);
        //Act
        boolean deletedTeam = teamService.deleteTeam(100L);
        //Assert
        assertFalse(deletedTeam);
    }

    @Test
    void shouldUpdateTeamWithCaptain() {
        //Arrange
        TeamRequestDto teamRequestDto = new TeamRequestDto();
        teamRequestDto.setTeamName("NewTeam1");
        teamRequestDto.setCaptainId(3L);
        teamRequestDto.setTeamPic("team1Pic");

        User captain = new User();
        ReflectionTestUtils.setField(captain, "id", 3L);
        captain.setFirstName("Captain");
        captain.setLastName("Leader");

        when(teamRepos.findById(1L)).thenReturn(Optional.of(team1));
        when(teamRepos.save(team1)).thenReturn(team1);
        when(userService.getSingleUser(3L)).thenReturn(captain);
        //Act
        Team updatedTeam = teamService.updateTeam(1L, teamRequestDto);
        //Assert
        assertNotNull(updatedTeam);
        assertEquals("NewTeam1", updatedTeam.getTeamName());
        assertEquals(3L, updatedTeam.getCaptain().getId());
        assertEquals("Captain", updatedTeam.getCaptain().getFirstName());
        assertEquals("Leader", updatedTeam.getCaptain().getLastName());
        assertEquals("team1Pic", updatedTeam.getTeamPic());
    }

    @Test
    void shouldUpdateTeamWithoutCaptain() {
        //Arrange
        TeamRequestDto teamRequestDto = new TeamRequestDto();
        teamRequestDto.setTeamName("NewTeam1");
        teamRequestDto.setTeamPic("team1Pic");

        when(teamRepos.findById(1L)).thenReturn(Optional.of(team1));
        when(teamRepos.save(team1)).thenReturn(team1);
        //Act
        Team updatedTeam = teamService.updateTeam(1L, teamRequestDto);
        //Assert
        assertNotNull(updatedTeam);
        assertEquals("NewTeam1", updatedTeam.getTeamName());
        assertEquals("team1Pic", updatedTeam.getTeamPic());
        assertNull(updatedTeam.getCaptain());
    }

    @Test
    void shouldNotFindTeamToUpdate() {
        //Arrange
        TeamRequestDto teamRequestDto = new TeamRequestDto();
        teamRequestDto.setTeamName("NewTeam1");
        teamRequestDto.setCaptainId(3L);
        teamRequestDto.setTeamPic("team1Pic");

        when(teamRepos.findById(1000L)).thenReturn(Optional.empty());
        //Act
        //Assert
        ResourceNotFoundException ex = assertThrows(ResourceNotFoundException.class, () -> {
            teamService.updateTeam(1000L, teamRequestDto);
        });
        assertEquals("Team 1000 not found", ex.getMessage());
    }

    @Test
    void shouldOnlyUpdateTeamName() {
        //Arrange
        TeamPatchDto newTeamInfo =  new TeamPatchDto();
        newTeamInfo.setTeamName("NewTeam1");

        when(teamRepos.findById(1L)).thenReturn(Optional.of(team1));
        when(teamRepos.save(team1)).thenReturn(team1);

        //Act
        Team updatedTeam = teamService.patchTeam(1L, newTeamInfo);
        //Assert
        assertEquals("NewTeam1", updatedTeam.getTeamName());
        assertEquals(LocalDate.of(2022,11,5), updatedTeam.getCreationDate());
        assertNull(updatedTeam.getTeamPic());
        assertNull(updatedTeam.getCaptain());
    }

    @Test
    void shouldOnlyUpdateCaptainId() {
        //Arrange
        TeamPatchDto newTeamInfo =  new TeamPatchDto();
        newTeamInfo.setCaptainId(3L);

        User captain = new User();
        ReflectionTestUtils.setField(captain, "id", 3L);
        captain.setFirstName("Captain");
        captain.setLastName("Leader");

        when(teamRepos.findById(1L)).thenReturn(Optional.of(team1));
        when(teamRepos.save(team1)).thenReturn(team1);
        when(userService.getSingleUser(3L)).thenReturn(captain);
        //Act
        Team updatedTeam = teamService.patchTeam(1L, newTeamInfo);
        //Assert
        assertEquals("team1", updatedTeam.getTeamName());
        assertEquals(LocalDate.of(2022,11,5), updatedTeam.getCreationDate());
        assertNull(updatedTeam.getTeamPic());
        assertEquals(updatedTeam.getCaptain(), captain);
        assertEquals(3L, updatedTeam.getCaptain().getId());
        assertEquals("Captain", updatedTeam.getCaptain().getFirstName());
        assertEquals("Leader", updatedTeam.getCaptain().getLastName());
    }

    @Test
    void shouldOnlyUpdateTeamPic() {
        //Arrange
        TeamPatchDto newTeamInfo =  new TeamPatchDto();
        newTeamInfo.setTeamPic("NewTeamPic1");

        when(teamRepos.findById(1L)).thenReturn(Optional.of(team1));
        when(teamRepos.save(team1)).thenReturn(team1);

        //Act
        Team updatedTeam = teamService.patchTeam(1L, newTeamInfo);
        //Assert
        assertEquals("team1", updatedTeam.getTeamName());
        assertEquals(LocalDate.of(2022,11,5), updatedTeam.getCreationDate());
        assertEquals("NewTeamPic1", updatedTeam.getTeamPic());
        assertNull(updatedTeam.getCaptain());
    }

    @Test
    void shouldOnlyUpdateTeamNameAndTeamPic() {
        //Arrange
        TeamPatchDto newTeamInfo =  new TeamPatchDto();
        newTeamInfo.setTeamName("NewTeam1");
        newTeamInfo.setTeamPic("NewTeamPic1");

        when(teamRepos.findById(1L)).thenReturn(Optional.of(team1));
        when(teamRepos.save(team1)).thenReturn(team1);

        //Act
        Team updatedTeam = teamService.patchTeam(1L, newTeamInfo);
        //Assert
        assertEquals("NewTeam1", updatedTeam.getTeamName());
        assertEquals(LocalDate.of(2022,11,5), updatedTeam.getCreationDate());
        assertEquals("NewTeamPic1", updatedTeam.getTeamPic());
        assertNull(updatedTeam.getCaptain());
    }

    @Test
    void shouldNotFindTeamToPatch() {
        //Arrange
        TeamPatchDto newTeamInfo =  new TeamPatchDto();
        newTeamInfo.setTeamName("NewTeam1");
        newTeamInfo.setTeamPic("NewTeamPic1");

        when(teamRepos.findById(1000L)).thenReturn(Optional.empty());
        //Act
        //Assert
        ResourceNotFoundException ex = assertThrows(ResourceNotFoundException.class, () -> {
            teamService.patchTeam(1000L, newTeamInfo);
        });
        assertEquals("Team 1000 not found", ex.getMessage());
    }
}