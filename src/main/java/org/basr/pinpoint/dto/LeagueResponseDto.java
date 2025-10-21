package org.basr.pinpoint.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.basr.pinpoint.enums.LeagueDay;
import org.basr.pinpoint.enums.LeagueDivision;

import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LeagueResponseDto {
    private Long id;
    private String leagueName;
    private LeagueDivision leagueDivision;
    private LeagueDay leagueDay;
    private LocalDate creationDate;
    private List<LeagueTeamInfoDto> leagueTeams;
}
