package org.basr.pinpoint.service;

import org.basr.pinpoint.dto.UserRequestDto;
import org.basr.pinpoint.dto.UserResponseDto;
import org.basr.pinpoint.dto.UserUpdateDto;
import org.basr.pinpoint.exception.ResourceNotFoundException;
import org.basr.pinpoint.helper.FileStorage;
import org.basr.pinpoint.helper.PasswordHelper;
import org.basr.pinpoint.mapper.UserMapper;
import org.basr.pinpoint.model.Player;
import org.basr.pinpoint.model.User;
import org.basr.pinpoint.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {

    private final UserRepository repos;
    private final RoleService roleService;
    private final FileStorage fileStorage;
    private final PlayerService playerService;

    public UserService(UserRepository repos, RoleService roleService, FileStorage fileStorage,  PlayerService playerService) {
        this.repos = repos;
        this.roleService = roleService;
        this.fileStorage = fileStorage;
        this.playerService = playerService;
    }

    public User createUser(UserRequestDto userRequestDto) {
        User user = UserMapper.toEntity(userRequestDto);
        user.setPassword(PasswordHelper.encodePassword(userRequestDto.getPassword()));
        roleService.assignDefaultRoleToUser(user);
        User savedUser = repos.save(user);

        Player player = playerService.createPlayer(savedUser);
        savedUser.setPlayer(player);

        return repos.save(savedUser);
    }

    public User getSingleUser(long id) {
        return this.repos.findById(id).orElseThrow(()-> new ResourceNotFoundException("User " + id + " not found"));
    }

    public Long getUserIdByEmail(String email) {
        return repos.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"))
                .getId();
    }

    public List<User> getUserByName(String firstName, String lastName) {
        List<User> users;
        firstName = StringUtils.hasText(firstName) ? firstName.trim() : null;
        lastName = StringUtils.hasText(lastName) ? lastName.trim() : null;

        if (firstName != null && lastName != null) {
            users = repos.findByFirstNameAndLastName(firstName, lastName);
        } else if (firstName != null) {
            users = repos.findByFirstName(firstName);
        } else if (lastName != null) {
            users = repos.findByLastName(lastName);
        } else {
            return List.of();
        }
        return users;
    }

    public List<User> getAllUsers() {
        return this.repos.findAll();
    }

    public User updateUser(long id, UserUpdateDto userUpdateDto) {
        User user = repos.findById(id).orElseThrow(() -> new ResourceNotFoundException("User " + id + " not found"));

        UserMapper.updateEntity(user, userUpdateDto);

        return repos.save(user);

    }

    public List<User> findByDobAfter(LocalDate date) {
        return this.repos.findByDobAfter(date);
    }

    public String uploadProfilePicture(Long id, MultipartFile file) {
        User user = repos.findById(id).orElseThrow(() -> new ResourceNotFoundException("User " + id + " not found"));

        String imageName = System.currentTimeMillis() + "_" + file.getOriginalFilename();
        Path path = Paths.get("uploads/profilepic/" + imageName);

        try {
            fileStorage.writeFile(path, file.getBytes());
        } catch (IOException e) {
            throw new RuntimeException("Image upload failed");
        }

        String imageUrl = "/images/profilepic/" + imageName;
        user.setProfilePic(imageUrl);
        repos.save(user);

        return imageUrl;
    }

    public void updatePassword(Long id, String newPassword) {
        User user = repos.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User " + id + " not found"));
        user.setPassword(PasswordHelper.encodePassword(newPassword));
        repos.save(user);
    }
}
