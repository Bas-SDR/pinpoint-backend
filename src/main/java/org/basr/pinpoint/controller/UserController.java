package org.basr.pinpoint.controller;

import jakarta.validation.Valid;
import org.basr.pinpoint.dto.UserRequestDto;
import org.basr.pinpoint.dto.UserResponseDto;
import org.basr.pinpoint.helper.UriHelper;
import org.basr.pinpoint.mapper.UserMapper;
import org.basr.pinpoint.model.User;
import org.basr.pinpoint.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService service;

    public UserController(UserService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<UserResponseDto> createUser(@Valid @RequestBody UserRequestDto userRequestDto) {

        User user = this.service.createUser(userRequestDto);
        UserResponseDto userResponseDto = UserMapper.toResponseDto(user);

        URI location = UriHelper.buildUri(user.getId());

        return ResponseEntity.created(location).body(userResponseDto);
    }

    @GetMapping
    public ResponseEntity<List<UserResponseDto>> getAllUsers() {
        return ResponseEntity.ok(UserMapper.toResponseDtoList(this.service.getAllUsers()));
    }


    @GetMapping("/{id}")
    public ResponseEntity<UserResponseDto> getUserById(@PathVariable Long id) {
        return ResponseEntity.ok(UserMapper.toResponseDto(this.service.getSingleUser(id)));
    }

    @GetMapping("/after")
    public ResponseEntity<List<UserResponseDto>> getAllUsersByAfter(@RequestParam LocalDate date) {
        return ResponseEntity.ok(UserMapper.toResponseDtoList(this.service.findByDobAfter(date)));
    }
    //TODO Add new Admin Dto so dob can be sent as well.

    @PutMapping("/{id}")
    public ResponseEntity<UserResponseDto> updateUser(@PathVariable Long id, @Valid @RequestBody UserRequestDto userRequestDto) {
        User updatedUser = service.updateUser(id, userRequestDto);
        return ResponseEntity.ok(UserMapper.toResponseDto(updatedUser));
    }
}

