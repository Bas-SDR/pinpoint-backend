package org.basr.pinpoint.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.basr.pinpoint.enums.LeagueDay;
import org.basr.pinpoint.enums.LeagueDivision;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LeagueRequestDto {

    @NotBlank
    @Size(min = 6, max = 20)
    private String leagueName;
    @NotNull(message = "Division is required")
    private LeagueDivision leagueDivision;
    @NotNull(message = "League day is required")
    private LeagueDay leagueDay;
}
