package org.basr.pinpoint.dto;

import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.basr.pinpoint.enums.LeagueDay;
import org.basr.pinpoint.enums.LeagueDivision;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LeaguePatchDto {
@Size(min = 6, max = 20)
private String leagueName;
private LeagueDivision leagueDivision;
private LeagueDay leagueDay;
}
