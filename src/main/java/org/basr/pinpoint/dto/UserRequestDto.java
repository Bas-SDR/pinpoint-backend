package org.basr.pinpoint.dto;

import jakarta.validation.constraints.*;
import lombok.Data;

import java.time.LocalDate;

@Data
public class UserRequestDto {
    @NotBlank
    private String firstName;
    @NotBlank
    private String lastName;
    @NotBlank
    @Email
    private String email;
    @Past
    private LocalDate dob;
    @Size(min = 10, max = 12)
    @Pattern(
            regexp = "^(\\d{10}|\\+\\d{11,12})$",
            message = "Phone must be either 10 digits or '+' followed by country code and 9 digits"
    )
    private String phone;
}
