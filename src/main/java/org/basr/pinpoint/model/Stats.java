package org.basr.pinpoint.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "player_stats")
public class Stats {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int gamesPlayed;
    private int highestGame;
    private int highestSeries;
    private int totalPinfall;
    private int perfectGames;

    public double getAverageScore() {
        if (gamesPlayed == 0)
            return 0;
        return  (double)totalPinfall / gamesPlayed;
    }

    @OneToOne(mappedBy = "stats")
    @EqualsAndHashCode.Exclude
    Player player;
}