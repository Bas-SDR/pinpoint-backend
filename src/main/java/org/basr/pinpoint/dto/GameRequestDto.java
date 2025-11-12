package org.basr.pinpoint.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GameRequestDto {
    @NotNull
    @PastOrPresent
    private LocalDate datePlayed;
    @NotNull
    @Min(0)
    @Max(300)
    private Integer pinfall;
    @NotNull
    private Integer gameNumber;
    @NotNull
    private Long playerId;
    @NotNull
    private Long teamId;
    @NotNull
    private Long leagueId;
}
