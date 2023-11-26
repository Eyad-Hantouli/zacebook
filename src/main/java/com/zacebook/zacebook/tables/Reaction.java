package com.zacebook.zacebook.tables;

import com.zacebook.zacebook.enums.Reactions;
import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name="REACTION")
public class Reaction {
    // PK FK
    @Id
    @ManyToOne
    @JoinColumn(name = "person_id", nullable = false)
    private Person author;

    // PK FK
    @Id
    @ManyToOne
    @JoinColumn(name = "post_id", nullable = false)
    private Post post;

    private Reactions type;
    private LocalDate date;
}
