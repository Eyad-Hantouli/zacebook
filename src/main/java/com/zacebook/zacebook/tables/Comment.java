package com.zacebook.zacebook.tables;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name="COMMENT")
public class Comment {
    // PK
    @Id
    @SequenceGenerator(
            name= "comment_sequence",
            sequenceName = "comment_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "comment_sequence"
    )
    private Long id;

    private String textualContent;
    private LocalDate date;

    // FK
    @ManyToOne
    @JoinColumn(name = "person_id", nullable = false)
    private Person author;

    // FK
    @ManyToOne
    @JoinColumn(name = "post_id", nullable = false)
    private Post post;
}
