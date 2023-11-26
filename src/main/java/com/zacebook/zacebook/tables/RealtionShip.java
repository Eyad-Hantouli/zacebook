package com.zacebook.zacebook.tables;

import com.zacebook.zacebook.enums.RelationShips;
import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name="RELATION_SHIP")
public class RealtionShip {
    // PK FK
    @Id
    @ManyToOne
    @JoinColumn(name = "sender_id", referencedColumnName = "user_name")
    private Person source;

    // PK FK
    @Id
    @ManyToOne
    @JoinColumn(name = "receiver_id", referencedColumnName = "user_name")
    private Person target;

    private RelationShips type;
    private LocalDate date;

}
