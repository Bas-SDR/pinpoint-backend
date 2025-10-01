package org.basr.pinpoint.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)
    private long id;
    private String firstName;
    private String lastName;
    @Column(unique = true)
    private String email;
    private String phone;
    private LocalDate dob;
    private String profilePic;

    public User(String firstName, String lastName, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }

    public User(String firstName, String lastName, String email, String phone, LocalDate dob) {
        this(firstName, lastName, email);
        this.phone = phone;
        this.dob = dob;
    }
}
