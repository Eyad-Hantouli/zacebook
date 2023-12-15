package com.zacebook.zacebook.tables;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.zacebook.zacebook.compositekeys.RelationShipKey;
import com.zacebook.zacebook.enums.RelationShips;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

@Entity
@Table(name="relation_ships")
@IdClass(RelationShipKey.class)
public class RelationShip {
    // PK FK
    @Id
    @ManyToOne
    @JoinColumn(name = "source_id", referencedColumnName = "user_name", nullable = false)
    private User source;

    // PK FK
    @Id
    @ManyToOne
    @JoinColumn(name = "target_id", referencedColumnName = "user_name", nullable = false)
    private User target;

    private RelationShips type;

    private LocalDate date;

    public RelationShip() {
    }

    public RelationShip(User source,
                        User target,
                        RelationShips type,
                        LocalDate date) {
        this.source = source;
        this.target = target;
        this.type = type;
        this.date = date;
    }

    public User getSource() {
        return source;
    }

    public void setSource(User source) {
        this.source = source;
    }

    public User getTarget() {
        return target;
    }

    public void setTarget(User target) {
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

    @JsonIgnore
    public Map<String, Object> getAllData() {
        Map<String, Object> data = new HashMap<>();
        data.put("source", this.getSource().getUserName());
        data.put("target", this.getTarget().getUserName());
        data.put("type", this.getType());
        data.put("date", this.getDate());

        return data;
    }
}
