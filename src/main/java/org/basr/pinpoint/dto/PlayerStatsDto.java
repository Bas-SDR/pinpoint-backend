package org.basr.pinpoint.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PlayerStatsDto {
    private int gamesPlayed;
    private int highestGame;
    private int highestSeries;
    private int totalPinfall;
    private int perfectGames;
    private double averageScore;
}
