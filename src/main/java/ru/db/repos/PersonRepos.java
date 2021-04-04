package ru.db.repos;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import ru.db.models.Person;
import ru.db.models.PersonInfo;

import java.util.List;


public interface PersonRepos extends CrudRepository<Person, Integer> {
    @Query(value = "SELECT p.name, p.surname, count(*)\n" +
            "FROM person p\n" +
            "    JOIN person_event pe ON pe.person_id = p.id\n" +
            "    JOIN event e ON pe.event_id = e.id\n" +
            "GROUP BY p.name,p.surname", nativeQuery = true)
    List<PersonInfo> getInfo();
}



