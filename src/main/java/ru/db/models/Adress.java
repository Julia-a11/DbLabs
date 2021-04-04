package ru.db.models;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "adress", indexes = {@Index(name = "test5", columnList = "city")})
public class Adress {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String city;
    private String street;
    private int houseNumber;
    @OneToOne(mappedBy = "adress")
    private Event event;
}
