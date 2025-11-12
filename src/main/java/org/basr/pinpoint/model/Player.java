package org.basr.pinpoint.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "players")
public class Player {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)
    private Long id;

    @OneToOne(mappedBy = "player")
    @EqualsAndHashCode.Exclude
    private User user;

    @ManyToMany(mappedBy = "players")
    @EqualsAndHashCode.Exclude
    private Set<Team> teams = new HashSet<>();

    @OneToOne(cascade = CascadeType.ALL)
    @EqualsAndHashCode.Exclude
    @JoinColumn(name = "stats_id")
    private Stats stats;

    @OneToMany(mappedBy = "player")
    @EqualsAndHashCode.Exclude
    private List<Game> games = new ArrayList<>();
}
