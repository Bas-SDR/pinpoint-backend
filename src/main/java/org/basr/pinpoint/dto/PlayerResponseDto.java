package org.basr.pinpoint.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PlayerResponseDto {
    private Long id;
    private String firstName;
    private String lastName;
    private List<PlayerTeamInfoDto> teams;
    private PlayerStatsDto stats;
    private String profilePicture;
}
