package ru.db.repos;

import org.springframework.data.repository.CrudRepository;
import ru.db.models.Note;

public interface NoteRepos extends CrudRepository<Note, Integer> {
}
