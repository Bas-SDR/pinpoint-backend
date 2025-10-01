package org.basr.pinpoint.mapper;

import org.basr.pinpoint.dto.UserRequestDto;
import org.basr.pinpoint.dto.UserResponseDto;
import org.basr.pinpoint.model.User;

public class UserMapper {
    public static User toEntity(UserRequestDto userRequestDto) {
        User user = new User(userRequestDto.firstName, userRequestDto.lastName, userRequestDto.email, userRequestDto.phone, userRequestDto.dob);
        return user;
    }

    public static UserResponseDto toResponseDto(User user) {
        UserResponseDto userResponseDto = new UserResponseDto();
        userResponseDto.id = user.getId();
        userResponseDto.firstName = user.getFirstName();
        userResponseDto.lastName = user.getLastName();
        return userResponseDto;
    }
}
