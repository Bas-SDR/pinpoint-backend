package org.basr.pinpoint.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GameCreateDto {
    private LocalDate datePlayed;
    private int pinfall;
    private int gameNumber;
    private long playerId;
    private long teamId;
    private long leagueId;
}
