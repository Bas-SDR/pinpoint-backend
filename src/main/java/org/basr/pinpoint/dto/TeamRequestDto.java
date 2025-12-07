package org.basr.pinpoint.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TeamRequestDto {
    @NotBlank
    @Size(min = 1, max = 20)
    private String teamName;
    @NotNull
    private Long captainId;
}
