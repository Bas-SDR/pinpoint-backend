package org.basr.pinpoint.controller;

import jakarta.validation.Valid;
import org.basr.pinpoint.dto.*;
import org.basr.pinpoint.helper.UriHelper;
import org.basr.pinpoint.mapper.UserMapper;
import org.basr.pinpoint.model.User;
import org.basr.pinpoint.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

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

    @GetMapping("/search")
    public ResponseEntity<List<UserResponseDto>> getUserByName(@RequestParam(required = false) String firstName, @RequestParam(required = false) String lastName) {

        return ResponseEntity.ok(UserMapper.toResponseDtoList(service.getUserByName(firstName, lastName)));
    }

    @GetMapping("/{id}/private")
    @PreAuthorize("#id == authentication.principal.id or hasRole('ADMIN')")
    public ResponseEntity<UserFullResponseDto> getUserPrivateById(@PathVariable Long id) {
        return ResponseEntity.ok(UserMapper.toFullUser(this.service.getSingleUser(id)));
    }

    @PutMapping("/{id}")
    @PreAuthorize("#id == authentication.principal.id or hasRole('ADMIN')")
    public ResponseEntity<UserResponseDto> updateUser(@PathVariable Long id, @Valid @RequestBody UserUpdateDto userUpdateDto) {
        User updatedUser = service.updateUser(id, userUpdateDto);
        return ResponseEntity.ok(UserMapper.toResponseDto(updatedUser));
    }

    //https://stackabuse.com/uploading-files-with-spring-boot/
    @PostMapping("/{id}/profile-pic")
    public ResponseEntity<String> uploadProfilePic(@PathVariable Long id, @RequestParam("file") MultipartFile file) {
        String imageUrl = service.uploadProfilePicture(id, file);
        return ResponseEntity.ok(imageUrl);
    }

    @PatchMapping("/{id}/password")
    @PreAuthorize("#id == authentication.principal.id or hasRole('ADMIN')")
    public ResponseEntity<String> updatePassword(
            @PathVariable Long id,
            @RequestBody @Valid UserPasswordPatchDto userPasswordPatchDto) {

        service.updatePassword(id, userPasswordPatchDto.getPassword());
        return ResponseEntity.ok("Password successfully updated");
    }
}

