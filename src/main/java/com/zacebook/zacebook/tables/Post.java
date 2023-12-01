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
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "person_id", nullable = false)
    private Person author;

    public Post() {
    }

    public Post(Long id, String textualContent, LocalDate date, Person author) {
        this.id = id;
        this.textualContent = textualContent;
        this.date = date;
        this.author = author;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTextualContent() {
        return textualContent;
    }

    public void setTextualContent(String textualContent) {
        this.textualContent = textualContent;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Person getAuthor() {
        return author;
    }

    public void setAuthor(Person author) {
        this.author = author;
    }
}
