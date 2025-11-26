package org.basr.pinpoint.controller;

import org.basr.pinpoint.dto.AdminUserResponseDto;
import org.basr.pinpoint.mapper.AdminUserMapper;
import org.basr.pinpoint.service.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdminController {


    private final UserService userService;
//    private final PlayerService playerService;
//    private final GameService gameService;
//    private final TeamService teamService;
//    private final LeagueService leagueService;

    public AdminController(UserService userService
//            , PlayerService playerService
//            , GameService gameService
//            , TeamService teamService
//            , LeagueService leagueService
    ) {
        this.userService = userService;
//        this.playerService = playerService;
//        this.gameService = gameService;
//        this.teamService = teamService;
//        this.leagueService = leagueService;
    }

    @GetMapping("/users/after")
    public ResponseEntity<List<AdminUserResponseDto>> getAllUsersByAfter(@RequestParam LocalDate date) {
        return ResponseEntity.ok(AdminUserMapper.toAdminUserResponseDtoList(this.userService.findByDobAfter(date)));
    }

}
