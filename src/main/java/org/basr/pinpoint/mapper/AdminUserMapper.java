package org.basr.pinpoint.mapper;

import org.basr.pinpoint.dto.AdminUserResponseDto;
import org.basr.pinpoint.model.User;

import java.util.List;
import java.util.stream.Collectors;

public class AdminUserMapper {

    public static AdminUserResponseDto toAdminUserResponseDto (User user){
        AdminUserResponseDto adminUserResponseDto = new AdminUserResponseDto();
        adminUserResponseDto.setId(user.getId());
        adminUserResponseDto.setFirstName(user.getFirstName());
        adminUserResponseDto.setLastName(user.getLastName());
        adminUserResponseDto.setEmail(user.getEmail());
        adminUserResponseDto.setDob(user.getDob());

        return adminUserResponseDto;
    }

    public static List<AdminUserResponseDto> toAdminUserResponseDtoList(List<User> users) {
        return users.stream().map(AdminUserMapper::toAdminUserResponseDto).collect(Collectors.toList());
    }
}
