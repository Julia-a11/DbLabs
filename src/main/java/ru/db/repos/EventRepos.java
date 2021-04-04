package ru.db.repos;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import ru.db.models.Event;
import ru.db.models.EventDateInfo;
import ru.db.models.EventInfo;
import java.util.List;

public interface EventRepos extends CrudRepository<Event, Integer> {
@Query(value = "SELECT e.name, a.city, et.type, count(*)\n" +
        "FROM event e\n" +
        "JOIN adress a on e.adress_id = a.id\n" +
        "JOIN event_type et on e.event_type_id = et.id\n" +
        "JOIN person_event pe on e.id = pe.event_id\n" +
        "JOIN person p on pe.person_id = p.id\n" +
        "GROUP BY e.name,a.city,et.type", nativeQuery = true)
    List<EventInfo> getEventInfo();

    @Query(value = "SELECT e.date, count(*)\n" +
        "FROM event e\n" +
        "GROUP BY e.date", nativeQuery = true)
    List<EventDateInfo> getEventDateInfo();

}
