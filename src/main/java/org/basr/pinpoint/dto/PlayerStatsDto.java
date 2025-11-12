package org.basr.pinpoint.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PlayerStatsDto {
    private Integer gamesPlayed;
    private Integer highestGame;
    private Integer highestSeries;
    private Integer totalPinfall;
    private Integer perfectGames;
    private Double averageScore;
}
