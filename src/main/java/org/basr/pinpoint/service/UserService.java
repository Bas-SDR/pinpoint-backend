package org.basr.pinpoint.service;

import org.basr.pinpoint.dto.UserRequestDto;
import org.basr.pinpoint.exception.ResourceNotFoundException;
import org.basr.pinpoint.mapper.UserMapper;
import org.basr.pinpoint.model.User;
import org.basr.pinpoint.repository.UserRepository;
import org.springframework.stereotype.Service;

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
}
