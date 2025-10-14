package org.basr.pinpoint.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "teams")
public class Team {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(unique = true)
    private String teamName;
    private String teamPic;
    private LocalDate creationDate;

    public Team(String teamName) {
        this.teamName = teamName;
        this.creationDate = LocalDate.now();
    }

    public Team(String teamName, String teamPic) {
        this.teamName = teamName;
        this.teamPic = teamPic;
    }

    @ManyToOne
    @JoinColumn(name = "captain_id")
    private User captain;

    @ManyToMany
    @JoinTable(
            name = "players_teams",
            joinColumns = @JoinColumn(name = "team_id"),
            inverseJoinColumns = @JoinColumn(name = "player_id")
    )
    private List<Player> players = new ArrayList<>();
}
