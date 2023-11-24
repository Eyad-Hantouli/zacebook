package com.zacebook.zacebook.tables;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name="POST")
public class Post {
    // PK
    @Id
    @SequenceGenerator(
            name= "post_sequence",
            sequenceName = "post_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "post_sequence"
    )
    private Long id;

    private String textualContent;
    private LocalDate date;

    //FK
    @ManyToOne
    @JoinColumn(name = "person_id", nullable = false)
    private Person author;
}
