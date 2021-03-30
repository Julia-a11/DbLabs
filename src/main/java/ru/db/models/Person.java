package ru.db.models;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name = "person")
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String surname;
    private String login;
    private String password;
    @OneToMany(mappedBy = "person", cascade = CascadeType.ALL)
    private List<Note> noteList;
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "person_event",
            joinColumns = {@JoinColumn(name = "person_id")},
            inverseJoinColumns = {@JoinColumn(name = "event_id")}
    )
    private List<Event> eventList;
}
