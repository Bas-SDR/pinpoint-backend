package org.basr.pinpoint.mapper;

import org.basr.pinpoint.dto.UserFullResponseDto;
import org.basr.pinpoint.dto.UserRequestDto;
import org.basr.pinpoint.dto.UserResponseDto;
import org.basr.pinpoint.dto.UserUpdateDto;
import org.basr.pinpoint.model.User;

import java.util.List;
import java.util.stream.Collectors;

public class UserMapper {
    public static User toEntity(UserRequestDto userRequestDto) {
        User user = new User(
                userRequestDto.getFirstName(),
                userRequestDto.getLastName(),
                userRequestDto.getEmail(),
                userRequestDto.getPassword(),
                userRequestDto.getPhone(),
                userRequestDto.getDob());
        return user;
    }

    public static void updateEntity(User user, UserUpdateDto userUpdateDto) {
        user.setFirstName(userUpdateDto.getFirstName());
        user.setLastName(userUpdateDto.getLastName());
        user.setDob(userUpdateDto.getDob());
        user.setEmail(userUpdateDto.getEmail());
        user.setPhone(userUpdateDto.getPhone());
    }

    public static UserResponseDto toResponseDto(User user) {
        UserResponseDto userResponseDto = new UserResponseDto();
        userResponseDto.setId(user.getId());
        userResponseDto.setFirstName(user.getFirstName());
        userResponseDto.setLastName(user.getLastName());
        userResponseDto.setProfilePicture(user.getProfilePic());
        return userResponseDto;
    }


    public static UserFullResponseDto toFullUser(User user) {
        UserFullResponseDto userFullResponseDto = new UserFullResponseDto();
        userFullResponseDto.setId(user.getId());
        userFullResponseDto.setFirstName(user.getFirstName());
        userFullResponseDto.setLastName(user.getLastName());
        userFullResponseDto.setEmail(user.getEmail());
        userFullResponseDto.setPhone(user.getPhone());
        userFullResponseDto.setDob(user.getDob());
        userFullResponseDto.setProfilePic(user.getProfilePic());

        return userFullResponseDto;
    }

    public static List<UserResponseDto> toResponseDtoList(List<User> users) {
        return users.stream().map(UserMapper::toResponseDto).collect(Collectors.toList());
    }
}
