package org.basr.pinpoint.service;

import org.basr.pinpoint.dto.UserRequestDto;
import org.basr.pinpoint.exception.ResourceNotFoundException;
import org.basr.pinpoint.helper.PasswordHelper;
import org.basr.pinpoint.mapper.UserMapper;
import org.basr.pinpoint.model.User;
import org.basr.pinpoint.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.List;

@Service
public class UserService {

    private final UserRepository repos;
    private final RoleService roleService;

    public UserService(UserRepository repos, RoleService roleService) {
        this.repos = repos;
        this.roleService = roleService;
    }

    public User createUser(UserRequestDto userRequestDto) {
        User user = UserMapper.toEntity(userRequestDto);
        user.setPassword(PasswordHelper.encodePassword(userRequestDto));
        roleService.assignDefaultRoleToUser(user);
        return repos.save(user);
    }

    public User getSingleUser(long id) {
        return this.repos.findById(id).orElseThrow(()-> new ResourceNotFoundException("User " + id + " not found"));
    }

    public List<User> getAllUsers() {
        return this.repos.findAll();
    }

    public User updateUser(long id, UserRequestDto userRequestDto) {
        User user = repos.findById(id).orElseThrow(() -> new ResourceNotFoundException("User " + id + " not found"));

        UserMapper.updateEntity(user, userRequestDto);
        user.setPassword(PasswordHelper.encodePassword(userRequestDto));
        return repos.save(user);

    }

    public List<User> findByDobAfter(LocalDate date) {
        return this.repos.findByDobAfter(date);
    }

    public String uploadProfilePicture(Long userId, MultipartFile file) {
        User user = repos.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User not found"));

        String imageName = file.getOriginalFilename();
        Path path = Paths.get("uploads/" + imageName);

        try {
            Files.createDirectories(path.getParent());
            Files.write(path, file.getBytes());
        } catch (IOException e) {
            throw new RuntimeException("Image upload failed");
        }

        String imageUrl = "/images/profilepic/" + imageName;
        user.setProfilePic(imageUrl);
        repos.save(user);

        return imageUrl;
    }
}
