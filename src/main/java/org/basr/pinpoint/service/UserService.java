package org.basr.pinpoint.service;

import org.basr.pinpoint.dto.UserRequestDto;
import org.basr.pinpoint.exception.ResourceNotFoundException;
import org.basr.pinpoint.mapper.UserMapper;
import org.basr.pinpoint.model.User;
import org.basr.pinpoint.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class UserService {

    private final UserRepository repos;

    public UserService(UserRepository repos) {
        this.repos = repos;
    }

    public User createUser(UserRequestDto userRequestDto) {
        return this.repos.save(UserMapper.toEntity(userRequestDto));
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
        return repos.save(user);
    }

    public List<User> findByDobAfter(LocalDate date) {
        return this.repos.findByDobAfter(date);
    }

}
