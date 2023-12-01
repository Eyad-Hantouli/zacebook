package com.zacebook.zacebook.tables;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name="RELATION_SHIP")
public class RealtionShip {
    // PK FK
    @Id
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "sender_id", referencedColumnName = "user_name", nullable = false)
    private Person source;

    // PK FK
    @Id
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "receiver_id", referencedColumnName = "user_name", nullable = false)
    private Person target;

    private Short type;
    private LocalDate date;

    public RealtionShip() {
    }

    public RealtionShip(Person source, Person target, Short type, LocalDate date) {
        this.source = source;
        this.target = target;
        this.type = type;
        this.date = date;
    }

    public Person getSource() {
        return source;
    }

    public void setSource(Person source) {
        this.source = source;
    }

    public Person getTarget() {
        return target;
    }

    public void setTarget(Person target) {
        this.target = target;
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
