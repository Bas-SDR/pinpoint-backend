package org.basr.pinpoint.model;

import jakarta.persistence.*;
import lombok.*;
import org.basr.pinpoint.enums.LeagueDay;
import org.basr.pinpoint.enums.LeagueDivision;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "leagues")
public class League {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)
    private Long id;

    @Column(unique = true)
    private String leagueName;
    @Enumerated(EnumType.STRING)
    private LeagueDivision leagueDivision;
    @Enumerated(EnumType.STRING)
    private LeagueDay leagueDay;
    @Column(updatable = false)
    private LocalDate creationDate;

    @ManyToMany
    @EqualsAndHashCode.Exclude
    @JoinTable(
            name = "league_teams",
            joinColumns = @JoinColumn(name = "league_id"),
            inverseJoinColumns = @JoinColumn(name = "team_id")
    )
    private Set<Team> teams = new HashSet<>();

    @OneToMany(mappedBy = "league")
    @EqualsAndHashCode.Exclude
    List<Game> games = new ArrayList<>();

    public League(String leagueName, LeagueDivision leagueDivision,  LeagueDay leagueDay) {
        this.leagueName = leagueName;
        this.leagueDivision = leagueDivision;
        this.leagueDay = leagueDay;
        this.creationDate = LocalDate.now();
    }
}
