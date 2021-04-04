package ru.db.repos;

import org.springframework.data.repository.CrudRepository;
import ru.db.models.Adress;

public interface AdressRepos extends CrudRepository<Adress, Integer> {
}
