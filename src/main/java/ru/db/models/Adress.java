package ru.db.models;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "adress")
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
