package org.basr.pinpoint.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.PastOrPresent;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GamePatchDto {
    @PastOrPresent
    private LocalDate datePlayed;
    @Min(0)
    @Max(300)
    private Integer pinfall;
    private Integer gameNumber;
    private Long playerId;
    private Long teamId;
    private Long leagueId;
}
