package org.basr.pinpoint.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "players")
public class Player {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)
    private int playerId;
    private List<Integer> teamIds;
    private List<Integer> leagueIds;
    private double averageScore;
    private Stats stats;

    @OneToOne(mappedBy = "users")
    private User user;

    @ManyToOne
    @JoinColumn(name = "team_id")
    Team myTeam;

    @Data
    public static class Stats {
        private int highestGame;
        private int highestSeries;
        private int totalPinfall;
        private int averagePinfall;
        private int perfectGames;
    }
}
