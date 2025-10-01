package org.basr.pinpoint.mapper;

import org.basr.pinpoint.dto.UserRequestDto;
import org.basr.pinpoint.dto.UserResponseDto;
import org.basr.pinpoint.model.User;

import java.util.List;
import java.util.stream.Collectors;

public class UserMapper {
    public static User toEntity(UserRequestDto userRequestDto) {
        User user = new User(userRequestDto.getFirstName(), userRequestDto.getLastName(), userRequestDto.getEmail(), userRequestDto.getPhone(), userRequestDto.getDob());
        return user;
    }

    public static UserResponseDto toResponseDto(User user) {
        UserResponseDto userResponseDto = new UserResponseDto();
        userResponseDto.id = user.getId();
        userResponseDto.firstName = user.getFirstName();
        userResponseDto.lastName = user.getLastName();
        return userResponseDto;
    }

    public static List<UserResponseDto> toResponseDtoList(List<User> users) {
        return users.stream().map(UserMapper::toResponseDto).collect(Collectors.toList());
    }
}
