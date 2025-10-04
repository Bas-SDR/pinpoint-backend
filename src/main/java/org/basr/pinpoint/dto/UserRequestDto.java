package org.basr.pinpoint.dto;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserRequestDto {
    @NotBlank
    @Size(min=1, max=128)
    private String firstName;
    @NotBlank
    @Size(min=1, max=128)
    private String lastName;
    @NotBlank
    @Email
    private String email;
    @Past
    private LocalDate dob;
    @NotBlank
    @Size(min=8, max=64)
    private String password;
    @Size(min = 10, max = 12)
    @Pattern(
            regexp = "^(\\d{10}|\\+\\d{11,12})$",
            message = "Phone must be either 10 digits or '+' followed by country code and 9 digits"
    )
    private String phone;
    private String profilePic;
}
