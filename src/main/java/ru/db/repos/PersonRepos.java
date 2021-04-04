package ru.db.repos;

import org.springframework.data.repository.CrudRepository;
import ru.db.models.Person;


public interface PersonRepos extends CrudRepository<Person, Integer> {
}
