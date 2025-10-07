package org.basr.pinpoint.service;

import org.basr.pinpoint.dto.UserRequestDto;
import org.basr.pinpoint.exception.ResourceNotFoundException;
import org.basr.pinpoint.mapper.UserMapper;
import org.basr.pinpoint.model.User;
import org.basr.pinpoint.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.basr.pinpoint.helper.PasswordHelper;

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

}
