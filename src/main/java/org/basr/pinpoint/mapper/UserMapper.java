package org.basr.pinpoint.mapper;

import org.basr.pinpoint.dto.UserRequestDto;
import org.basr.pinpoint.dto.UserResponseDto;
import org.basr.pinpoint.model.User;

import java.util.List;
import java.util.stream.Collectors;

public class UserMapper {
    public static User toEntity(UserRequestDto userRequestDto) {
        User user = new User(
                userRequestDto.getFirstName(),
                userRequestDto.getLastName(),
                userRequestDto.getEmail(),
                userRequestDto.getPhone(),
                userRequestDto.getDob());
        return user;
    }

    public static void updateEntity(User user, UserRequestDto userRequestDto) {
        user.setFirstName(userRequestDto.getFirstName());
        user.setLastName(userRequestDto.getLastName());
        user.setDob(userRequestDto.getDob());
        user.setEmail(userRequestDto.getEmail());
        user.setPhone(userRequestDto.getPhone());
        user.setProfilePic(userRequestDto.getProfilePic());
    }


    public static UserResponseDto toResponseDto(User user) {
        UserResponseDto userResponseDto = new UserResponseDto();
        userResponseDto.setId(user.getId());
        userResponseDto.setFirstName(user.getFirstName());
        userResponseDto.setLastName(user.getLastName());
        return userResponseDto;
    }

    public static List<UserResponseDto> toResponseDtoList(List<User> users) {
        return users.stream().map(UserMapper::toResponseDto).collect(Collectors.toList());
    }
}
