package org.basr.pinpoint.service;

import org.basr.pinpoint.dto.TeamCreateDto;
import org.basr.pinpoint.dto.TeamPatchDto;
import org.basr.pinpoint.dto.TeamPlayerResponseDto;
import org.basr.pinpoint.dto.TeamRequestDto;
import org.basr.pinpoint.exception.ResourceNotFoundException;
import org.basr.pinpoint.helper.FileStorage;
import org.basr.pinpoint.model.Player;
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
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDate;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
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

    @Mock
    PlayerService playerService;

    @Mock
    FileStorage fileStorage;

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
    }

    @Test
    void shouldUpdateTeamWithoutCaptain() {
        //Arrange
        TeamRequestDto teamRequestDto = new TeamRequestDto();
        teamRequestDto.setTeamName("NewTeam1");

        when(teamRepos.findById(1L)).thenReturn(Optional.of(team1));
        when(teamRepos.save(team1)).thenReturn(team1);
        //Act
        Team updatedTeam = teamService.updateTeam(1L, teamRequestDto);
        //Assert
        assertNotNull(updatedTeam);
        assertEquals("NewTeam1", updatedTeam.getTeamName());
        assertNull(updatedTeam.getCaptain());
    }

    @Test
    void shouldNotFindTeamToUpdate() {
        //Arrange
        TeamRequestDto teamRequestDto = new TeamRequestDto();
        teamRequestDto.setTeamName("NewTeam1");
        teamRequestDto.setCaptainId(3L);

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
    void shouldNotFindTeamToPatch() {
        //Arrange
        TeamPatchDto newTeamInfo =  new TeamPatchDto();
        newTeamInfo.setTeamName("NewTeam1");
        //Act
        when(teamRepos.findById(1000L)).thenReturn(Optional.empty());
        //Assert
        ResourceNotFoundException ex = assertThrows(ResourceNotFoundException.class, () -> {
            teamService.patchTeam(1000L, newTeamInfo);
        });
        assertEquals("Team 1000 not found", ex.getMessage());
    }

    @Test
    void shouldUploadTeamPicture() throws IOException {
        // Arrange
        MultipartFile file = Mockito.mock(MultipartFile.class);
        when(file.getOriginalFilename()).thenReturn("teamLogo.png");
        when(file.getBytes()).thenReturn(new byte[]{1, 2, 3});

        when(teamRepos.findById(team1.getId())).thenReturn(Optional.of(team1));
        when(teamRepos.save(team1)).thenReturn(team1);

        // Act
        String url = teamService.uploadTeamPicture(team1.getId(), file);

        // Assert
        assertTrue(url.startsWith("/images/teampic/"));
        assertTrue(url.endsWith("_teamLogo.png"));
        assertEquals(url, team1.getTeamPic());
    }

    @Test
    void shouldNotFindTeamForUpload() {
        // Arrange
        MultipartFile file = Mockito.mock(MultipartFile.class);

        when(teamRepos.findById(1000L)).thenReturn(Optional.empty());
        //Act
        //Assert
        ResourceNotFoundException ex = assertThrows(ResourceNotFoundException.class, () -> {
            teamService.uploadTeamPicture(1000L, file);
        });
        assertEquals("Team 1000 not found", ex.getMessage());

        // Assert
    }

    @Test
    void shouldNotUploadTeamPicture() throws IOException {
        MultipartFile file = Mockito.mock(MultipartFile.class);
        when(file.getOriginalFilename()).thenReturn("teamLogo.png");
        when(file.getBytes()).thenReturn(new byte[]{1,2,3});

        when(teamRepos.findById(1L)).thenReturn(Optional.of(team1));

        Mockito.doThrow(new IOException("Test")).when(fileStorage).writeFile(Mockito.any(), Mockito.any());

        //Act
        //Assert
        RuntimeException ex = assertThrows(RuntimeException.class, () -> {
            teamService.uploadTeamPicture(1L, file);
        });

        assertEquals("Image upload failed", ex.getMessage());
    }

    @Test
    void shouldRemovePlayerFromTeam() {
        //Arrange
        User playerUser = new User();
        ReflectionTestUtils.setField(playerUser, "id", 10L);
        playerUser.setFirstName("Captain");
        playerUser.setLastName("Leader");

        Player player = new Player();
        ReflectionTestUtils.setField(player, "id", 10L);
        player.setUser(playerUser);

        team1.setPlayers(new HashSet<>(Set.of(player)));

        //Act
        when(teamRepos.findById(1L)).thenReturn(Optional.of(team1));
        when(playerService.getPlayerById(10L)).thenReturn(player);

        teamService.removePlayer(1L, 10L);

        //Assert
        assertFalse(team1.getPlayers().contains(player));
    }

    @Test
    void shouldThrowExceptionPlayerPartOfTeam() {
        //Arrange
        team1.setPlayers(new HashSet<>());

        Player player = new Player();
        ReflectionTestUtils.setField(player, "id", 1000L);

        //Act
        when(teamRepos.findById(team1.getId())).thenReturn(Optional.of(team1));
        when(playerService.getPlayerById(1000L)).thenReturn(player);

        //Assert
        ResourceNotFoundException ex = assertThrows(ResourceNotFoundException.class, () ->
                teamService.removePlayer(team1.getId(), 1000L));

        assertEquals("Player 1000 is not in team " + team1.getId(), ex.getMessage());
    }

    @Test
    void shouldThrowExceptionTeamPlayersNull() {
        //Arrange
        ReflectionTestUtils.setField(team1, "id", 1L);
        team1.setPlayers(null);

        Player player = new Player();
        ReflectionTestUtils.setField(player, "id", 1000L);

        //Act
        when(teamRepos.findById(team1.getId())).thenReturn(Optional.of(team1));
        when(playerService.getPlayerById(1000L)).thenReturn(player);

        //Assert
        ResourceNotFoundException ex = assertThrows(ResourceNotFoundException.class,
                () -> teamService.removePlayer(team1.getId(), 1000L));

        assertEquals("Player 1000 is not in team 1", ex.getMessage());
    }

    @Test
    void shouldAddPlayerToTeam() {
        //Arrange
        User playerUser = new User();
        ReflectionTestUtils.setField(playerUser, "id", 10L);
        playerUser.setFirstName("Captain");
        playerUser.setLastName("Leader");

        Player player = new Player();
        ReflectionTestUtils.setField(player, "id", 10L);
        player.setUser(playerUser);

        team1.setPlayers(new HashSet<>());

        //Act
        when(teamRepos.findById(1L)).thenReturn(Optional.of(team1));
        when(playerService.getPlayerById(10L)).thenReturn(player);

        TeamPlayerResponseDto teamPlayerResponseDto = teamService.addPlayer(1L, 10L);

        //Assert
        assertTrue(team1.getPlayers().contains(player));
        assertEquals("team1", teamPlayerResponseDto.getTeamName());
        assertEquals("Captain", teamPlayerResponseDto.getPlayerFirstName());
        assertEquals("Leader", teamPlayerResponseDto.getPlayerLastName());
    }

    @Test
    void shouldNotAddPlayerToTeam() {
        // Arrange
        Player player = new Player();
        ReflectionTestUtils.setField(player, "id", 1000L);

        team1.setPlayers(new HashSet<>(Set.of(player)));

        //Act
        when(teamRepos.findById(team1.getId())).thenReturn(Optional.of(team1));
        when(playerService.getPlayerById(1000L)).thenReturn(player);

        //Assert
        IllegalStateException ex = assertThrows(IllegalStateException.class,
                () -> teamService.addPlayer(team1.getId(), 1000L));
    }
}