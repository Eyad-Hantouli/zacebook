package com.zacebook.zacebook.tables;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.Collection;

@Entity
@Table(name="PERSON")
public class Person {
    // PK
    @Id
    @Column(name="user_name")
    private String userName;

    //Unique
    @Column(unique = true)
    private String email;

    private String firstName;
    private String lastName;
    private String password;
    private LocalDate dob;
    private String address;

    // FK
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "active_profile_picture_id", referencedColumnName = "id")
    private ProfilePicture activeProfilePicture;
}
