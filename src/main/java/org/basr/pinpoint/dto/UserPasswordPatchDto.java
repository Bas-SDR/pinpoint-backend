package org.basr.pinpoint.dto;

import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class UserPasswordPatchDto {
    @Size(min=8, max=64)
    private String password;
}
