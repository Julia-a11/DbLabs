package ru.db.util;

import de.vandermeer.asciitable.AsciiTable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.db.models.Note;
import ru.db.models.Person;
import ru.db.repos.NoteRepos;

import java.sql.Date;
import java.util.List;
import java.util.Scanner;

@Component
public class NoteMenu {
    private final NoteRepos repos;
    private final PersonMenu personMenu;

    @Autowired
    public NoteMenu(NoteRepos repos, PersonMenu personMenu) {
        this.repos = repos;
        this.personMenu = personMenu;
    }

    public void save(Scanner scanner) {
        Note note = new Note();

        System.out.print("Введите сообщение: ");
        scanner.skip("[\r\n]+");
        String message = scanner.nextLine();
        note.setMessage(message);
        note.setDate(new Date(System.currentTimeMillis()));

        System.out.print("Выберите пользователя: ");
        note.setPerson(choosePerson(scanner));

        repos.save(note);
        System.out.println("Заметка сохранена");
    }

    public void findAll() {
        List<Note> noteList = (List<Note>) repos.findAll();

        AsciiTable table = new AsciiTable();
        table.addRule();
        table.addRow("ID", "Сообщение", "Дата", "Пользователь");
        table.addRule();
        for (Note note : noteList) {
            table.addRow(note.getId(), note.getMessage(), note.getDate(),
                    note.getPerson().getLogin());
            table.addRule();
        }
        System.out.println(table.render());
    }

    public void findById(Scanner scanner) {
        Note note = checkNote(scanner);
        if (note == null) {
            return;
        }

        AsciiTable table = new AsciiTable();
        table.addRule();
        table.addRow("ID", "Сообщение", "Дата", "Пользователь");
        table.addRule();
        table.addRow(note.getId(), note.getMessage(), note.getDate(),
                note.getPerson().getLogin());
        table.addRule();
        System.out.println(table.render());
    }

    public void update(Scanner scanner) {
        Note note = checkNote(scanner);
        if (note == null) {
            return;
        }

        System.out.print("Введите сообщение: ");
        scanner.skip("[\r\n]+");
        String message = scanner.nextLine();
        note.setMessage(message);
        note.setDate(new Date(System.currentTimeMillis()));

        System.out.print("Выберите пользователя: ");
        note.setPerson(choosePerson(scanner));

        repos.save(note);
        System.out.println("Заметка обновлена");
    }

    public void delete(Scanner scanner) {
        Note note = checkNote(scanner);
        if (note == null) {
            return;
        }

        repos.delete(note);
        System.out.println("Заметка удалена");
    }

    private Note checkNote(Scanner scanner) {
        System.out.println("Введите ID");
        int id = scanner.nextInt();

        if (repos.findById(id).isPresent()) {
            return repos.findById(id).get();
        } else {
            System.out.println("Заметка не найдена");
            return null;
        }
    }

    private Person choosePerson(Scanner scanner) {
        personMenu.findAll();
        return personMenu.findById(scanner);
    }
}
