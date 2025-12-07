package org.basr.pinpoint.service;

import org.basr.pinpoint.dto.UserRequestDto;
import org.basr.pinpoint.exception.ResourceNotFoundException;
import org.basr.pinpoint.helper.FileStorage;
import org.basr.pinpoint.helper.PasswordHelper;
import org.basr.pinpoint.mapper.UserMapper;
import org.basr.pinpoint.model.User;
import org.basr.pinpoint.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.List;

@Service
public class UserService {

    private final UserRepository repos;
    private final RoleService roleService;
    private final FileStorage fileStorage;

    public UserService(UserRepository repos, RoleService roleService,  FileStorage fileStorage) {
        this.repos = repos;
        this.roleService = roleService;
        this.fileStorage = fileStorage;
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

    public String uploadProfilePicture(Long id, MultipartFile file) {
        User user = repos.findById(id).orElseThrow(() -> new ResourceNotFoundException("User " + id + " not found"));

        String imageName = System.currentTimeMillis() + "_" + file.getOriginalFilename();
        Path path = Paths.get("uploads/profilepic" + imageName);

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
}
