package ru.db.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name = "person",indexes = {@Index(name = "test", columnList = "login")})
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
    @ManyToMany(mappedBy = "personList", fetch = FetchType.EAGER)
    private List<Event> eventList;
}
