package org.basr.pinpoint.model;

//import jakarta.persistence.Entity;
//import jakarta.persistence.GeneratedValue;
//import jakarta.persistence.GenerationType;
//import jakarta.persistence.Id;
import lombok.*;

import java.time.LocalDate;

@Data
//@NoArgsConstructor
//@AllArgsConstructor
//@Entity
public class User {
    private static long counter = 1L;
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)
    private long id;
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private LocalDate dob;
    private String profilePic;

    //TODO Delete when automatic ID generation is added
    public User(String firstName, String lastName, String email) {
        this.id = counter++;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }
}
