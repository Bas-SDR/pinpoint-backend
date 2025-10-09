package org.basr.pinpoint.helper;

import org.basr.pinpoint.dto.UserRequestDto;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class PasswordHelper {
    private static final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public static String encodePassword(UserRequestDto dto) {
        return passwordEncoder.encode(dto.getPassword());
    }
}