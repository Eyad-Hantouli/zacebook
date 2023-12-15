package com.zacebook.zacebook.tables;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

@Entity
@Table(name="comments")
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
    @JoinColumn(name = "user_id", nullable = false)
    private User author;

    // FK
    @ManyToOne
    @JoinColumn(name = "post_id", nullable = false)
    private Post post;

    public Comment() {
    }

    public Comment(String textualContent,
                   Post post,
                   User author,
                   LocalDate date) {
        this.textualContent = textualContent;
        this.author = author;
        this.post = post;
        this.date = date;
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

    @JsonIgnore
    public Map<String, Object> getAllData() {
        Map<String, Object> data = new HashMap<>();
        data.put("id", this.getId());
        data.put("textualContent", this.getTextualContent());
        data.put("date", this.getDate());
        data.put("author", this.getAuthor().getUserName());
        data.put("post", this.getPost().getId());

        return data;
    }


}
