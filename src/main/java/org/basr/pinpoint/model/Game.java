package org.basr.pinpoint.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "games")
public class Game {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate datePlayed;
    private int pinfall;
    private int gameNumber;

    @CreationTimestamp
    private LocalDate createdDate;

    @ManyToOne
    @JoinColumn(name = "player_id")
    @EqualsAndHashCode.Exclude
    private Player player;

    @ManyToOne
    @JoinColumn(name = "team_id")
    @EqualsAndHashCode.Exclude
    private Team team;

    @ManyToOne
    @JoinColumn(name = "league_id")
    @EqualsAndHashCode.Exclude
    private League league;

}
