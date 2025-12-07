package org.basr.pinpoint.dto;

import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TeamPatchDto {
    @Size(min = 1, max = 20)
    private String teamName;
    private Long captainId;
}
