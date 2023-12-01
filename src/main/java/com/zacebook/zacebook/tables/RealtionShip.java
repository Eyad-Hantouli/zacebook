package com.zacebook.zacebook.tables;

import com.zacebook.zacebook.enums.RelationShips;
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

    private RelationShips type;
    private LocalDate date;

    public RealtionShip() {
    }

    public RealtionShip(Person source, Person target, RelationShips type, LocalDate date) {
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

    public RelationShips getType() {
        return type;
    }

    public void setType(RelationShips type) {
        this.type = type;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }
}
