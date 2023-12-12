package com.zacebook.zacebook.tables;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

@Entity
@Table(name="profile_pictures")
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
    private Boolean isActive;
    private LocalDate date;

    // FK
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id", nullable = false)
    private User author;

    public ProfilePicture() {
    }

    public ProfilePicture(String link,
                          User author,
                          LocalDate date) {
        this.link = link;
        this.author = author;
        this.date = date;
        this.isActive = true;
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

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public Boolean getActive() {
        return isActive;
    }

    public void setActive(Boolean active) {
        isActive = active;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    @JsonIgnore
    public Map<String, Object> getAllData() {
        Map<String, Object> data = new HashMap<>();
        data.put("id", this.getId());
        data.put("link", this.getLink());
        data.put("isActive", this.getActive());
        data.put("date", this.getDate());
        data.put("author", this.getAuthor().getUserName());

        return data;
    }
}
