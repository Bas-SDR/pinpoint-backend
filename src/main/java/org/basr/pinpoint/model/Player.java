package org.basr.pinpoint.model;

import lombok.Data;
import java.util.List;

@Data
public class Player {
    private int playerId;
//    private User user;
    private List<Integer> teamIds;
    private List<Integer> leagueIds;
    private double averageScore;
    private Stats stats;

    @Data
    public static class Stats {
        private int highestGame;
        private int highestSeries;
        private int totalPinfall;
        private int averagePinfall;
        private int perfectGames;
    }
}
