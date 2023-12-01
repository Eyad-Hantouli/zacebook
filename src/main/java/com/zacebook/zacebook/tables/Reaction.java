package com.zacebook.zacebook.tables;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name="REACTION")
public class Reaction {
    // PK FK
    @Id
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "person_id", nullable = false)
    private Person author;

    // PK FK
    @Id
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "post_id", nullable = false)
    private Post post;

    private Short type;
    private LocalDate date;

    public Reaction() {
    }

    public Reaction(Person author, Post post, Short type, LocalDate date) {
        this.author = author;
        this.post = post;
        this.type = type;
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

    public Short getType() {
        return type;
    }

    public void setType(Short type) {
        this.type = type;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }
}
