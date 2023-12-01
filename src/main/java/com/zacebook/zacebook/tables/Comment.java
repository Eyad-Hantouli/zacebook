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
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "person_id", nullable = false)
    private Person author;

    // FK
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "post_id", nullable = false)
    private Post post;

    public Comment() {
    }

    public Comment(String textualContent, LocalDate date, Person author, Post post) {
        this.textualContent = textualContent;
        this.date = date;
        this.author = author;
        this.post = post;
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

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }


}
