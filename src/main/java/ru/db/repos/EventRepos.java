package ru.db.repos;

import org.springframework.data.repository.CrudRepository;
import ru.db.models.Event;

public interface EventRepos extends CrudRepository<Event, Integer> {
}
