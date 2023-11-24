package com.zacebook.zacebook.tables;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name="RELATION_SHIP")
public class RealtionShip {
    // PK FK
    @Id
    @ManyToOne
    @JoinColumn(name = "sender_id", referencedColumnName = "id")
    private Person source;

    // PK FK
    @Id
    @ManyToOne
    @JoinColumn(name = "receiver_id", referencedColumnName = "id")
    private Person target;

    private Short type;
    private LocalDate date;

}
