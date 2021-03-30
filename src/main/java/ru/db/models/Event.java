package ru.db.models;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Data
@Entity
@Table(name = "event")
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private Date date;
    @ManyToMany(mappedBy = "eventList")
    private List<Person> personList;
    @ManyToOne
    @JoinColumn(name = "event_type_id")
    private EventType eventType;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "adress_id", referencedColumnName = "id")
    private Adress adress;
}
