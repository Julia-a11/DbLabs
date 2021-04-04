package ru.db.repos;

import org.springframework.data.repository.CrudRepository;
import ru.db.models.EventType;

public interface EventTypeRepos extends CrudRepository<EventType, Integer> {
}
