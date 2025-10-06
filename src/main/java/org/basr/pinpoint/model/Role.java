package org.basr.pinpoint.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "roles")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)
    private Long id;
    @Column(nullable = false, unique = true)
    private String rolename;

    @ManyToMany(mappedBy = "roles")
    //https://stackoverflow.com/questions/55920191/why-the-hashcode-is-forcing-my-jpa-mapping-to-fetch-the-child-entities-even-on
    @EqualsAndHashCode.Exclude
    private Set<User> users = new HashSet<>();

    public Role(String rolename) {
        this.rolename = rolename;
    }

}