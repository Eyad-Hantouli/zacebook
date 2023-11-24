package com.zacebook.zacebook.tables;

import jakarta.persistence.*;

@Entity
@Table(name="PROFILE_PICTURE")
public class ProfilePicture {
    // PK
    @Id
    private Long id;

    private String link;

    // FK
    @ManyToOne
    @JoinColumn(name = "person_id", nullable = false)
    private Person author;
}
