package ru.db.models;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name = "eventType", indexes = {@Index(name = "test6", columnList = "type")})
public class EventType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String type;
    private int priority;
    @OneToMany(mappedBy = "eventType", cascade = CascadeType.ALL)
    private List<Event> eventList;
}
