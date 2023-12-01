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
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "person_id", nullable = false)
    private Person author;

    public ProfilePicture() {
    }

    public ProfilePicture(Long id, String link, Person author) {
        this.id = id;
        this.link = link;
        this.author = author;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public Person getAuthor() {
        return author;
    }

    public void setAuthor(Person author) {
        this.author = author;
    }
}
