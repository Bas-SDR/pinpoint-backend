package org.basr.pinpoint.dto;

import jakarta.validation.constraints.*;

import java.time.LocalDate;

public class UserRequestDto {
    @NotBlank
    public String firstName;
    @NotBlank
    public String lastName;
    @NotBlank
    @Email
    public String email;
    @Past
    public LocalDate dob;
    @Size(min = 10, max = 12)
    @Pattern(
            regexp = "^(\\d{10}|\\+\\d{11,12})$",
            message = "Phone must be either 10 digits or '+' followed by country code and 9 digits"
    )
    public String phone;
}
