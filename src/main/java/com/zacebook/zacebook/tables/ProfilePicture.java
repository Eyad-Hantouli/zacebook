package com.zacebook.zacebook.tables;

import jakarta.persistence.*;

@Entity
@Table(name="PROFILE_PICTURE")
public class ProfilePicture {
    // PK
    @Id
    @SequenceGenerator(
            name= "profile_picture_sequence",
            sequenceName = "profile_picture_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "profile_picture_sequence"
    )
    private Long id;

    private String link;

    // FK
    @ManyToOne
    @JoinColumn(name = "person_id", nullable = false)
    private Person author;
}
