package org.basr.pinpoint.dto;

import jakarta.validation.constraints.*;
import lombok.Data;

import java.time.LocalDate;

@Data
public class UserPasswordPatchDto {
    @Size(min=8, max=64)
    private String password;
}
