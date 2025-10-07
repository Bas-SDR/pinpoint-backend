package org.basr.pinpoint.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="teams")
public class Team {
    @Id
    @GeneratedValue
    private int id;

    @Column(unique = true)
    private String name;

    @OneToMany(mappedBy = "team")
    private List<Player> members;
}
