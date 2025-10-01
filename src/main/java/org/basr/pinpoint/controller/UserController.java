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


//
//    @GetMapping("/after")
//    public ResponseEntity<List<User>> getAllUsersByAfter(@RequestParam LocalDate date) {
//        return ResponseEntity.ok(this.repos.findByDobAfter(date));
//    }
//
//    @PutMapping("/{id}")
//    public ResponseEntity<User> updateUser(@PathVariable Long id, @RequestBody User user) {
//        if (this.userMap.containsKey(id)) {
//            this.userMap.put(id, user);
//            return ResponseEntity.ok(user);
//        }
//        else {
//            return ResponseEntity.notFound().build();
//        }
//    }
}
