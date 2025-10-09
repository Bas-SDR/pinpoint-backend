package org.basr.pinpoint.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="teams")
public class Team {
    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
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


    //TODO Uncomment once Team class is finished and Player is worked on.
//    @OneToMany(mappedBy = "team")
//    private List<Player> players = new ArrayList<>();
}
