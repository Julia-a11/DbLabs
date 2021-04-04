package ru.db.models;


import lombok.Data;

import javax.persistence.*;
import java.sql.Date;

@Data
@Entity
@Table(name = "note",indexes = {@Index(name = "test2", columnList = "person_id")})
public class Note {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String message;
    private Date date;
    @ManyToOne
    @JoinColumn(name = "person_id")
    private Person person;
}
