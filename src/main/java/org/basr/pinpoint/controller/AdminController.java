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

    public AdminController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/users/after")
    public ResponseEntity<List<AdminUserResponseDto>> getAllUsersByAfter(@RequestParam LocalDate date) {
        return ResponseEntity.ok(AdminUserMapper.toAdminUserResponseDtoList(this.userService.findByDobAfter(date)));
    }

}
