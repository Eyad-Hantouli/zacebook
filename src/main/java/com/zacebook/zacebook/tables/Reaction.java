package com.zacebook.zacebook.tables;

import com.zacebook.zacebook.compositekeys.ReactionKey;
import com.zacebook.zacebook.enums.Reactions;
import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name="reactions")
@IdClass(ReactionKey.class)
public class Reaction {
    // PK FK
    @Id
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id", nullable = false)
    private User author;

    // PK FK
    @Id
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "post_id", nullable = false)
    private Post post;

    private Reactions type;
    private LocalDate date;

    public Reaction() {
    }

    public Reaction(User author,
                    Post post,
                    Reactions type,
                    LocalDate date) {
        this.author = author;
        this.post = post;
        this.type = type;
        this.date = date;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }

    public Reactions getType() {
        return type;
    }

    public void setType(Reactions type) {
        this.type = type;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }
}
