package com.zacebook.zacebook.tables;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.zacebook.zacebook.compositekeys.ReactionKey;
import com.zacebook.zacebook.enums.Reactions;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

@Entity
@Table(name="reactions")
@IdClass(ReactionKey.class)
public class Reaction {
    // PK FK
    @Id
    @ManyToOne
    @JoinColumn(name = "author_id", referencedColumnName = "user_name", nullable = false)
    private User author;

    // PK FK
    @Id
    @ManyToOne
    @JoinColumn(name = "post_id", referencedColumnName = "id", nullable = false)
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

    @JsonIgnore
    public Map<String, Object> getAllData() {
        Map<String, Object> data = new HashMap<>();
        data.put("author", this.getAuthor().getUserName());
        data.put("type", this.getType());
        data.put("date", this.getDate());

        return data;
    }
}
